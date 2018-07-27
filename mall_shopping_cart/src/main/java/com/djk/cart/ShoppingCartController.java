package com.djk.cart;

import com.djk.utils.LoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dujinkai on 2018/7/26.
 * 购物车控制器
 */
@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    /**
     * 注入购物车服务接口
     */
    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 注入
     * request代理对象
     */
    @Autowired
    private HttpServletRequest request;

    /**
     * 新增购物车接口
     *
     * @param shoppingCart 购物车
     * @return 1 成功  -1 会员不存在 -2 商品不存在 -3 库存不足 -4 参数错误
     */
    @PostMapping
    public int addShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        shoppingCart.setCustomerId(LoginUtils.getInstance().getUserId(request));
        return shoppingCartService.addShoppingCart(shoppingCart);
    }

    /**
     * 更新购物车数量
     *
     * @param shoppingCart 购物车
     * @return 1 成功 -4 参数错误 0 失败 -5 购物车不存在 -2 商品不存在  -3 库存不足
     */
    @PostMapping("/num")
    public int updateShoppingCartNum(@RequestBody ShoppingCart shoppingCart) {
        shoppingCart.setCustomerId(LoginUtils.getInstance().getUserId(request));
        return shoppingCartService.updateShoppingCartNum(shoppingCart);
    }

    /**
     * 删除购物车信息
     *
     * @return 成功返回1 失败返回0
     */
    @DeleteMapping
    public int deleteShoppingCart(Long[] ids) {
        return shoppingCartService.deleteShoppingCart(LoginUtils.getInstance().getUserId(request), ids);
    }

    /**
     * 查询会员购物车信息
     *
     * @return 返回购物车信息
     */
    @GetMapping
    public ShoppingCartResponse queryShoppingCart() {
        return shoppingCartService.queryShoppingCart(LoginUtils.getInstance().getUserId(request), null);
    }

    /**
     * 根据购物车id查询会员购物车信息
     *
     * @param ids        购物车id
     * @return 返回购物车信息
     */
    @GetMapping("/ids")
    public ShoppingCartResponse queryShoppingCartByIds(Long[] ids) {
        return shoppingCartService.queryShoppingCart(LoginUtils.getInstance().getUserId(request), ids);
    }
}
