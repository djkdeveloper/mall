package com.djk.order;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by dujinkai on 2018/7/27.
 * 订单商品数据库接口
 */
@Mapper
public interface OrderSpuMapper {

    /**
     * 保存订单下的商品信息
     *
     * @param orderSpus 订单商品信息
     */
    void saveOrderSpus(List<OrderSpu> orderSpus);
}
