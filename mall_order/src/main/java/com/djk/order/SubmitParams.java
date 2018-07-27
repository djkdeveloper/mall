package com.djk.order;

import lombok.Data;
import org.apache.commons.lang.ArrayUtils;

import java.util.Objects;

/**
 * Created by dujinkai on 2018/7/26.
 * 提交订单参数
 */
@Data
public class SubmitParams {

    /**
     * 用户id
     */
    private Long customerId;

    /**
     * 收货地址id
     */
    private Long addressId;

    /**
     * 订单备注
     */
    private String remark;

    /**
     * 购物车id
     */
    private Long[] ids;

    /**
     * 校验参数的合法性
     *
     * @return 通过返回true  不通过返回false
     */
    public boolean validate() {
        return Objects.nonNull(this.customerId) && Objects.nonNull(this.addressId) && ArrayUtils.isNotEmpty(ids);
    }
}
