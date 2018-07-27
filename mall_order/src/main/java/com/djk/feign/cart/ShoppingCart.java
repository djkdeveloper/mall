package com.djk.feign.cart;

import com.djk.feign.goods.Spu;
import com.djk.utils.CustomLocalDateTimeDeserializer;
import com.djk.utils.CustomLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Objects;

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
     * 获得购物车价格
     *
     * @return 返回购物车价格
     */
    public BigDecimal getTotalPrice() {
        if (Objects.isNull(spu)) {
            return new BigDecimal(0);
        } else {
            return spu.getPrice().multiply(new BigDecimal(this.getNum())).setScale(2, RoundingMode.HALF_EVEN);
        }
    }

    /**
     * 获得单品的名称
     *
     * @return 返回商品的名称
     */
    public String getSpuName() {
        return Objects.nonNull(this.spu) ? spu.getName() : "";
    }

    /**
     * 获得商品的价格
     *
     * @return 返回商品的价格
     */
    public BigDecimal getSpuPrice() {
        return Objects.nonNull(this.spu) ? spu.getPrice() : new BigDecimal(0);
    }

    public String getSpuImage() {
        return Objects.nonNull(this.spu) ? spu.getPic() : "";
    }
}
