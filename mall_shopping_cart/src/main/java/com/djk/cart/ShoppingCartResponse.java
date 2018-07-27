package com.djk.cart;

import lombok.Data;

import java.util.List;

/**
 * Created by dujinkai on 2018/7/26.
 * 查询购物车返回实体类
 */
@Data
public class ShoppingCartResponse {

    /**
     * 购物车信息
     */
    private List<ShoppingCart> shoppingCarts;


    /**
     * 构造购物车信息
     *
     * @param shoppingCarts 购物车
     * @return 返回购物车信息
     */
    public static ShoppingCartResponse build(List<ShoppingCart> shoppingCarts) {
        ShoppingCartResponse shoppingCartResponse = new ShoppingCartResponse();
        shoppingCartResponse.shoppingCarts = shoppingCarts;
        return shoppingCartResponse;
    }
}
