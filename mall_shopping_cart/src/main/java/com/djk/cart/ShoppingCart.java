package com.djk.cart;

import com.djk.feign.goods.Spu;
import com.djk.utils.CustomLocalDateTimeDeserializer;
import com.djk.utils.CustomLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by dujinkai on 2018/7/26.
 * 购物车实体
 */
@Data
public class ShoppingCart {

    /**
     * 主键id
     */
    private long id;

    /**
     * 商品id
     */
    private long spuId;

    /**
     * 会员id
     */
    private long customerId;

    /**
     * 购买数量
     */
    private int num;

    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    /**
     * 商品信息
     */
    private Spu spu;

    /**
     * 校验参数
     *
     * @return 成功返回true  失败返回false
     */
    public boolean validate() {
        return 0 != spuId && 0 != customerId && 0 != num;
    }
}
