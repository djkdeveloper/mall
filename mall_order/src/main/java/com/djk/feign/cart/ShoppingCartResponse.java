package com.djk.feign.cart;

import lombok.Data;
import org.springframework.util.CollectionUtils;

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
     * 判断是否有购物车信息
     *
     * @return 有返回true 没有返回false
     */
    public boolean hasCarts() {
        return !CollectionUtils.isEmpty(shoppingCarts);
    }

}
