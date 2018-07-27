package com.djk.order;

import org.apache.ibatis.annotations.Mapper;

/**
 * Created by dujinkai on 2018/7/27.
 * 订单数据库接口
 */
@Mapper
public interface OrderMapper {

    /**
     * 保存订单信息
     *
     * @param order 订单信息
     * @return 成功返回1 失败返回0
     */
    int saveOrder(Order order);
}
