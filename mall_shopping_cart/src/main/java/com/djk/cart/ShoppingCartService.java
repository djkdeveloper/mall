package com.djk.cart;

/**
 * Created by dujinkai on 2018/7/26.
 * 购物车接口
 */
public interface ShoppingCartService {

    /**
     * 加入购物车
     *
     * @param shoppingCart 购物车信息
     * @return 1 成功  -1 会员不存在 -2 商品不存在 -3 库存不足 -4 参数错误 0 失败
     */
    int addShoppingCart(ShoppingCart shoppingCart);

    /**
     * 更新购物车数量
     *
     * @param shoppingCart 购物车信息
     * @return 1 成功 -4 参数错误 0 失败 -5 购物车不存在 -2 商品不存在  -3 库存不足
     */
    int updateShoppingCartNum(ShoppingCart shoppingCart);

    /**
     * 删除购物车信息
     *
     * @param customerId 会员id
     * @param ids        购物车id
     * @return 成功返回1 失败返回0
     */
    int deleteShoppingCart(long customerId, Long[] ids);

    /**
     * 查询用户的购物车
     *
     * @param customerId 会员id
     * @param ids        购物车id
     * @return 返回用户的购物车信息
     */
    ShoppingCartResponse queryShoppingCart(long customerId, Long[] ids);

}
