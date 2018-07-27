package com.djk.order;

/**
 * Created by dujinkai on 2018/7/26.
 * 订单服务接口
 */
public interface OrderService {

    /**
     * 提交订单
     *
     * @param submitParams 提交订单参数
     * @return -1 参数错误 1 成功 -2 商品库存不足
     */
    int submitOrder(SubmitParams submitParams);
}
