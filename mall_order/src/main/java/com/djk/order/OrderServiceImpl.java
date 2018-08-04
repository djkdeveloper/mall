package com.djk.order;

import com.djk.feign.cart.ShoppingCartService;
import com.djk.feign.goods.SpuService;
import com.djk.settlement.OrderSettlement;
import com.djk.settlement.OrderSettlementRequest;
import com.djk.settlement.SettlementService;
import com.djk.utils.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by dujinkai on 2018/7/26.
 * 订单服务接口实现
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    /**
     * 注入结算服务接口
     */
    @Autowired
    private SettlementService settlementService;

    /**
     * 注入订单数据库接口
     */
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 注入订单商品数据库接口
     */
    @Autowired
    private OrderSpuMapper orderSpuMapper;

    /**
     * 注入购物车远程服务接口
     */
    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 注入远程商品服务接口
     */
    @Autowired
    private SpuService spuService;

    /**
     * 分布式事务的考虑
     * <p>
     * 1。分布式事务比较难解决，尽量避免 这边可以避免，因为用的是同一个数据库所以可以使用本地事务，在订单模块可以直接操作减库存和删除购物车的操作 不调用商品服务和购物车服务提供的接口
     * 2。这边是尽量研究下分布式事务 所以 减库存和删除购物车还是走服务提供的接口
     * 思路 走强一致性 遇到服务之间的异常走补偿机制，如果遇到补偿超时 则走校验机制
     */
    @Transactional
    @Log
    @Override
    public int submitOrder(SubmitParams submitParams) {

        if (Objects.isNull(submitParams) || !submitParams.validate()) {
            log.error("submitOrder fail due to params is error...");
            return -1;
        }

        // 获得订单的结算信息
        OrderSettlement orderSettlement = settlementService.orderSettlement(OrderSettlementRequest.build(submitParams));

        // 校验获取结算信息是否成功
        if (Objects.isNull(orderSettlement) || !orderSettlement.isSuccess()) {
            log.error("submitOrder error du to orderSettlement fail....");
            return -1;
        }

        // 校验库存是否足够



        // 获得订单信息
        Order order = getOrder(orderSettlement, submitParams);

        // 保存订单
        orderMapper.saveOrder(order);

        // 设置订单id
        order.setOrderSpusOrderId();

        // 保存订单下的商品信息
        orderSpuMapper.saveOrderSpus(order.getOrderSpus());

        // 删除购物车
        shoppingCartService.deleteShoppingCart(submitParams.getIds());

        // 扣减库存
        spuService.reduceSpuStock(order.getOrderSpus().stream().map(OrderSpu::buildReduceSpu).collect(Collectors.toList()));

        return 1;
    }

    /**
     * 获得订单
     *
     * @param orderSettlement 订单结算信息
     * @return 返回订单
     */
    @Log
    private Order getOrder(OrderSettlement orderSettlement, SubmitParams submitParams) {
        return Order.build(orderSettlement, submitParams);
    }
}
