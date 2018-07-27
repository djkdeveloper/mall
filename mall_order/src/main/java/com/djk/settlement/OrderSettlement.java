package com.djk.settlement;

import com.djk.feign.cart.ShoppingCart;
import com.djk.feign.cart.ShoppingCartResponse;
import com.djk.feign.user.CustomerAddress;
import com.djk.order.OrderSpu;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by dujinkai on 2018/7/26.
 * 订单结算实体
 */
@Data
public class OrderSettlement {

    /**
     * 结果 -1失败  1 成功
     */
    private int result;

    /**
     * 总价格
     */
    private BigDecimal totalPrice;

    /**
     * 会员的收货地址
     */
    private List<CustomerAddress> customerAddresses;

    /**
     * 会员的默认收货地址
     */
    private CustomerAddress customerAddress;

    /**
     * 购物车响应实体
     */
    private ShoppingCartResponse shoppingCartResponse;

    /**
     * 构造失败订单结算实体
     *
     * @return 返回失败订单结算实体
     */
    public static OrderSettlement buildFail() {
        OrderSettlement orderSettlement = new OrderSettlement();
        orderSettlement.result = -1;
        return orderSettlement;
    }


    /**
     * 构造结算信息
     *
     * @param shoppingCartResponse 购物车信息
     * @param customerAddresses    会员收货地址
     * @return 返回结算信息
     */
    public static OrderSettlement build(ShoppingCartResponse shoppingCartResponse, List<CustomerAddress> customerAddresses) {
        OrderSettlement orderSettlement = new OrderSettlement();
        orderSettlement.result = 1;
        orderSettlement.shoppingCartResponse = shoppingCartResponse;
        orderSettlement.customerAddresses = customerAddresses;
        // 计算价格
        orderSettlement.calcPrice();
        return orderSettlement;
    }

    /**
     * 判断获取结算信息是否成功
     *
     * @return 成功返回true  失败返回false
     */
    public boolean isSuccess() {
        return this.result == 1;
    }

    /**
     * 计算价格
     */
    private void calcPrice() {
        if (Objects.nonNull(shoppingCartResponse) && !CollectionUtils.isEmpty(shoppingCartResponse.getShoppingCarts())) {
            this.totalPrice = shoppingCartResponse.getShoppingCarts().stream().map(ShoppingCart::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_EVEN);
        }
    }

    /**
     * 获得订单下的商品信息
     *
     * @return 返回订单下的桑帕信息
     */
    @JsonIgnore
    public List<OrderSpu> getOrderSpus() {
        if (Objects.isNull(this.shoppingCartResponse) || CollectionUtils.isEmpty(this.shoppingCartResponse.getShoppingCarts())) {
            return Collections.EMPTY_LIST;
        }

        return this.shoppingCartResponse.getShoppingCarts().stream().map(OrderSpu::build).collect(Collectors.toList());
    }
}
