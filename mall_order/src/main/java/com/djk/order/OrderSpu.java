package com.djk.order;

import com.djk.feign.cart.ShoppingCart;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by dujinkai on 2018/7/27.
 * 订单下的商品实体
 */
@Data
public class OrderSpu {

    /**
     * 主键id
     */
    private long id;

    /**
     * 订单id
     */
    private long orderId;

    /**
     * 商品id
     */
    private long spuId;

    /**
     * 购买数量
     */
    private int num;

    /**
     * 商品名称
     */
    private String spuName;

    /**
     * 商品价格
     */
    private BigDecimal spuPrice;

    /**
     * 商品图片
     */
    private String spuImage;

    /**
     * 构造订单下面的商品
     *
     * @param shoppingCart 购物车信息
     * @return 返回订单商品实体
     */
    public static OrderSpu build(ShoppingCart shoppingCart) {
        OrderSpu orderSpu = new OrderSpu();

        if (Objects.nonNull(shoppingCart)) {
            orderSpu.spuId = shoppingCart.getSpuId();
            orderSpu.num = shoppingCart.getNum();
            orderSpu.spuName = shoppingCart.getSpuName();
            orderSpu.spuPrice = shoppingCart.getSpuPrice();
            orderSpu.spuImage = shoppingCart.getSpuImage();
        }

        return orderSpu;
    }
}
