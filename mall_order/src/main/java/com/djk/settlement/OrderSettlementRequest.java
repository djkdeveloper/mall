package com.djk.settlement;

import com.djk.order.SubmitParams;
import lombok.Data;
import org.apache.commons.lang.ArrayUtils;

import java.util.Objects;

/**
 * Created by dujinkai on 2018/7/26.
 * 订单结算请求实体
 */
@Data
public class OrderSettlementRequest {

    /**
     * 购物车id
     */
    private Long[] ids;

    /**
     * 收货地址id
     */
    private Long addressId;

    /**
     * 会员id
     */
    private Long customerId;

    /**
     * 校验参数是否合法
     *
     * @return 合法返回true  不合法返回false
     */
    public boolean validate() {
        return ArrayUtils.isNotEmpty(this.ids) && Objects.nonNull(this.addressId) && Objects.nonNull(this.customerId);
    }

    /**
     * 构造订单结算实体
     *
     * @param submitParams 提交订单实体
     * @return 返回订单结算实体
     */
    public static OrderSettlementRequest build(SubmitParams submitParams) {
        OrderSettlementRequest orderSettlementRequest = new OrderSettlementRequest();
        if (Objects.nonNull(submitParams)) {
            orderSettlementRequest.setAddressId(submitParams.getAddressId());
            orderSettlementRequest.setCustomerId(submitParams.getCustomerId());
            orderSettlementRequest.setIds(submitParams.getIds());
        }
        return orderSettlementRequest;
    }

}
