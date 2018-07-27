package com.djk.settlement;

/**
 * Created by dujinkai on 2018/7/26.
 * 结算接口
 */
public interface SettlementService {

    /**
     * 查询用户结算信息
     *
     * @param orderSettlementRequest 结算信息请求实体
     * @return 返回用户结算信息
     */
    OrderSettlement orderSettlement(OrderSettlementRequest orderSettlementRequest);
}
