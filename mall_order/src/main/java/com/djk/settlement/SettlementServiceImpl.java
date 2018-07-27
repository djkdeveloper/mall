package com.djk.settlement;

import com.djk.feign.cart.ShoppingCartResponse;
import com.djk.feign.cart.ShoppingCartService;
import com.djk.utils.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by dujinkai on 2018/7/26.
 * 结算接口实现
 */
@Slf4j
@Service
public class SettlementServiceImpl implements SettlementService {

    /**
     * 注入购物车远程服务
     */
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Log
    @Override
    public OrderSettlement orderSettlement(OrderSettlementRequest orderSettlementRequest) {

        // 校验参数
        if (Objects.isNull(orderSettlementRequest) || !orderSettlementRequest.validate()) {
            log.error("orderSettlement fail due to params is error...");
            return OrderSettlement.buildFail();
        }

        // 查询购物车信息
        ShoppingCartResponse shoppingCartResponse = shoppingCartService.queryShoppingCartByIds(orderSettlementRequest.getIds());


        // 购物车不存在
        if (Objects.isNull(shoppingCartResponse) || !shoppingCartResponse.hasCarts()) {
            log.error("orderSettlement fail due to carts is empty....");
            return OrderSettlement.buildFail();
        }


        // 查询收货地址

        return OrderSettlement.build(shoppingCartResponse, null);
    }
}
