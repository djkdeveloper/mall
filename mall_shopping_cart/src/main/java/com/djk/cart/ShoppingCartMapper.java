package com.djk.cart;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by dujinkai on 2018/7/26.
 * 购物车数据库接口
 */
@Mapper
public interface ShoppingCartMapper {

    /**
     * 根据商品id和会员id查询购物车
     *
     * @param params 查询参数
     * @return 返回用户购物车信息
     */
    ShoppingCart queryBySpuIdAndCustomerId(Map<String, Object> params);

    /**
     * 更新购物车数量
     *
     * @param params 参数
     * @return 成功返回1 失败返回0
     */
    int updateShoppingCartNum(Map<String, Object> params);

    /**
     * 新增购物车数量
     *
     * @param shoppingCart 购物车信息
     * @return 成功返回1 失败返回0
     */
    int saveShoppingCart(ShoppingCart shoppingCart);

    /**
     * 删除购物车信息
     *
     * @param params 删除参数
     * @return 成功返回1 失败返回0
     */
    int deleteShoppingCart(Map<String, Object> params);

    /**
     * 查询会员的购物车信息
     *
     * @param customerId 会员id
     * @return 返回会员购物车
     */
    List<ShoppingCart> queryShoppingCart(long customerId);

    /**
     * 根据购物车id查询购物车信息
     *
     * @param params 查询参数
     * @return 返回购物车信息
     */
    List<ShoppingCart> queryShoppingCartByIds(Map<String, Object> params);
}
