package com.djk.order;

import com.djk.settlement.OrderSettlement;
import com.djk.utils.CustomLocalDateTimeDeserializer;
import com.djk.utils.CustomLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by dujinkai on 2018/7/26.
 * 订单实体类
 */
@Data
@Builder
public class Order {

    /**
     * 主键id
     */
    private long id;

    /**
     * 会员id
     */
    private long customerId;

    /**
     * 订单价格
     */
    private BigDecimal price;

    /**
     * 订单状态 1:待付款  （用户刚下单）2:代发货  （用户付完款 等待商城发货）3:代收货  （商城已经发货 等待用户确认收货） 4:已完成  5 取消订单
     */
    private String status;

    /**
     * 订单取消原因 1:现在不想买 2:商品价格较贵 3:价格波动 4:商品缺货 5:重复下单
     */
    private String cancelReason;

    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    /**
     * 支付时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime payTime;

    /**
     * 取消时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime cancelTime;

    /**
     * 确认收货时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime receivingTime;

    /**
     * 发货时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime deliveryTime;

    /**
     * 收货人姓名
     */
    private String receiptName;

    /**
     * 收货人地址
     */
    private String receiptAddress;

    /**
     * 收货人手机号码
     */
    private String receiptMobile;

    /**
     * 订单备注
     */
    private String remark;

    /**
     * 订单下的商品信息
     */
    private List<OrderSpu> orderSpus;

    /**
     * 设置订单下商品的订单id
     */
    public void setOrderSpusOrderId() {
        if (!CollectionUtils.isEmpty(this.orderSpus)) {
            this.orderSpus.forEach(orderSpu -> orderSpu.setOrderId(this.id));
        }
    }



    /**
     * 构造订单信息
     *
     * @param orderSettlement 订单结算信息
     * @param submitParams    提交订单参数
     * @return 返回订单信息
     */
    public static Order build(OrderSettlement orderSettlement, SubmitParams submitParams) {
        Order order = new Order();
        order.setCustomerId(submitParams.getCustomerId());
        order.setPrice(orderSettlement.getTotalPrice());
        order.setStatus("1");
        order.setCreateTime(LocalDateTime.now());
        order.setRemark(submitParams.getRemark());
        order.setOrderSpus(orderSettlement.getOrderSpus());
        return order;
    }
}
