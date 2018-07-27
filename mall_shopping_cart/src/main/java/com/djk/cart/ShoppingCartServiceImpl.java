package com.djk.cart;

import com.djk.feign.customer.CustomerService;
import com.djk.feign.goods.Spu;
import com.djk.feign.goods.SpuService;
import com.djk.utils.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by dujinkai on 2018/7/26.
 * 加入购物车服务接口
 */
@Service
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {

    /**
     * 注入会员服务接口
     */
    @Autowired
    private CustomerService customerService;


    /**
     * 注入远程商品服务接口
     */
    @Autowired
    private SpuService spuSerivce;

    /**
     * 注入购物车数据库接口
     */
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Log
    @Override
    public int addShoppingCart(ShoppingCart shoppingCart) {

        // 校验参数
        if (Objects.isNull(shoppingCart) || !shoppingCart.validate()) {
            log.error("addShoppingCart fail due to params is error....");
            return -4;
        }

        // 校验用户是否存在
        if (!isCustomerExist(shoppingCart.getCustomerId())) {
            log.error("addShoppingCart fail due to customer is not exist and customerid is :{}", shoppingCart.getCustomerId());
            return -1;
        }

        // 校验商品是否存在
        Spu spu = spuSerivce.querySpuById(shoppingCart.getSpuId());

        if (Objects.isNull(spu)) {
            log.error("addShoppingCart fail due to spu is not exist and spuId is :{}", shoppingCart.getSpuId());
            return -2;
        }

        // 购买的数量
        int num = shoppingCart.getNum();

        // 查询用户已有的购物车
        ShoppingCart alertyShoppingCart = this.queryBySpuIdAndCustomerId(shoppingCart.getCustomerId(), shoppingCart.getSpuId());

        if (Objects.nonNull(alertyShoppingCart)) {
            num += alertyShoppingCart.getNum();
        }

        // 判断库存是否足够
        if (!spu.validateStock(num)) {

            log.error("addShoppingCart fail due to stock is not enough and stock is :{} \r\n user buy num :{}", spu.getStock(), num);
            return -3;
        }

        // 购买的商品在购物车中存在 则更新购物车数量 否则信息购物车
        if (Objects.nonNull(alertyShoppingCart)) {
            return updateShoppingCartNum(alertyShoppingCart.getId(), shoppingCart.getNum(), shoppingCart.getCustomerId());
        } else {
            return saveShoppingCart(shoppingCart);
        }

    }


    @Log
    @Override
    public int updateShoppingCartNum(ShoppingCart shoppingCart) {
        // 校验参数
        if (Objects.isNull(shoppingCart) || !shoppingCart.validate()) {
            log.error("addShoppingCart fail due to params is error....");
            return -4;
        }

        // 查询用户已有的购物车
        ShoppingCart alertyShoppingCart = this.queryBySpuIdAndCustomerId(shoppingCart.getCustomerId(), shoppingCart.getSpuId());

        if (Objects.isNull(alertyShoppingCart)) {
            log.error("updateShoppingCartNum fail due to shoppingcart is not exist...");
            return -5;
        }


        // 校验商品是否存在
        Spu spu = spuSerivce.querySpuById(shoppingCart.getSpuId());

        if (Objects.isNull(spu)) {
            log.error("updateShoppingCartNum fail due to spu is not exist and spuId is :{}", shoppingCart.getSpuId());
            return -2;
        }

        // 判断库存是否足够
        if (!spu.validateStock(shoppingCart.getNum() + alertyShoppingCart.getNum())) {

            log.error("updateShoppingCartNum fail due to stock is not enough and stock is :{} ", spu.getStock());
            return -3;
        }

        return this.updateShoppingCartNum(alertyShoppingCart.getId(), shoppingCart.getNum(), shoppingCart.getCustomerId());
    }

    @Log
    @Override
    public int deleteShoppingCart(long customerId, Long[] ids) {
        if (ArrayUtils.isEmpty(ids)) {
            log.error("deleteShoppingCart fail due to shoppingCart is null...");
            return 0;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("customerId", customerId);
        params.put("ids", ids);
        return shoppingCartMapper.deleteShoppingCart(params);
    }


    @Log
    @Override
    public ShoppingCartResponse queryShoppingCart(long customerId, Long[] ids) {

        // 查询用户购物车信息
        List<ShoppingCart> shoppingCarts = queryShoppingCarts(customerId, ids);

        if (CollectionUtils.isEmpty(shoppingCarts)) {
            log.error("user has no  shopping cart....");
            return new ShoppingCartResponse();
        }

        shoppingCarts.stream().forEach(shoppingCart -> shoppingCart.setSpu(spuSerivce.querySpuById(shoppingCart.getSpuId())));

        // 查询用户购物车信息
        return ShoppingCartResponse.build(shoppingCarts);
    }

    /**
     * 新增购物车
     *
     * @param shoppingCart 购物车
     * @return 成功返回1 失败返回0
     */
    @Log
    private int saveShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartMapper.saveShoppingCart(shoppingCart);
    }

    /**
     * 修改购物车数量
     *
     * @param id         购物车id
     * @param num        购物车数量
     * @param customerId 会员id
     * @return 成功返回1 失败返回0
     */
    @Log
    private int updateShoppingCartNum(long id, int num, long customerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("num", num);
        params.put("customerId", customerId);
        return shoppingCartMapper.updateShoppingCartNum(params);
    }


    /**
     * 根据商品id车用户id查询购物车信息
     *
     * @param customerId 会员id
     * @param spuId      商品id
     * @return 返回购物车信息
     */
    @Log
    private ShoppingCart queryBySpuIdAndCustomerId(long customerId, long spuId) {
        Map<String, Object> params = new HashMap<>();
        params.put("customerId", customerId);
        params.put("spuId", spuId);
        return shoppingCartMapper.queryBySpuIdAndCustomerId(params);
    }

    /**
     * 判断会员是否存在 存在返回true  不存在返回false
     *
     * @param customerId 会员id
     * @return 存在返回true 不存在返回false
     */
    @Log
    private boolean isCustomerExist(long customerId) {
        return Objects.nonNull(customerService.queryCustomerById(customerId));
    }


    /**
     * 查询购物车信息
     *
     * @param customerId 会员ID
     * @param ids        购物车id
     * @return 返回购物车信息
     */
    @Log
    private List<ShoppingCart> queryShoppingCarts(long customerId, Long[] ids) {
        if (ArrayUtils.isEmpty(ids)) {
            return shoppingCartMapper.queryShoppingCart(customerId);
        } else {
            Map<String, Object> params = new HashMap<>();
            params.put("customerId", customerId);
            params.put("ids", ids);
            return shoppingCartMapper.queryShoppingCartByIds(params);
        }

    }
}
