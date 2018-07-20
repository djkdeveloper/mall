package com.djk.spu;

import com.djk.brand.Brand;
import com.djk.category.Category;
import com.djk.utils.CustomLocalDateTimeDeserializer;
import com.djk.utils.CustomLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by dujinkai on 2018/7/12.
 * 商品实体类
 */
@Data
public class Spu {

    /**
     * 主键id
     */
    private long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 库存
     */
    private int stock;

    /**
     * 品牌id
     */
    private int brandId;

    /**
     * 一级分类id
     */
    private long firstCateId;

    /**
     * 二级分类id
     */
    private long secondCateId;

    /**
     * 三级分类id
     */
    private long thirdCateId;

    /**
     * 图片地址
     */
    private String pic;

    /**
     * 删除标记
     */
    private String delFlag;

    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime modifyTime;

    /**
     * 商品的三级分类
     */
    private Category thirdCategory;

    /**
     * 商品的品牌
     */
    private Brand brand;

}
