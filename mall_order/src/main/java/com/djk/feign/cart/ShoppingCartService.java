package com.djk.feign.cart;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by dujinkai on 2018/7/26.
 * 购物车远程服务接口
 */
@FeignClient("mall-shopping-cart")
public interface ShoppingCartService {

    /**
     * 查询购物车信息
     *
     * @param ids 购物车id
     * @return 返回购物车信息
     */
    @RequestMapping(value = "/cart/ids", method = RequestMethod.GET)
    ShoppingCartResponse queryShoppingCartByIds(@RequestParam("ids") Long[] ids);

    /**
     * 删除购物车信息
     *
     * @param ids 购物车id
     */
    @RequestMapping(value = "/cart", method = RequestMethod.DELETE)
    void deleteShoppingCart(@RequestParam("ids") Long[] ids);
}
