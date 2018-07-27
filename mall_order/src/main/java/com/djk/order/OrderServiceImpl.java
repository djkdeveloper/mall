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

        // 库存扣除成功标记
        List<Integer> list = new ArrayList<>();

        // 扣除商品库存
        order.getOrderSpus().forEach(orderSpu -> {
            // 说明库存不足
            if (spuService.reduceSpuStock(orderSpu.getSpuId(), orderSpu.getNum()) > 0) {
                list.add(1);
            }
        });

        return list.size() == order.getOrderSpus().size() ? 1 : -2;
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
