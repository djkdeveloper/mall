package com.djk.feign;

import com.djk.utils.CustomLocalDateTimeDeserializer;
import com.djk.utils.CustomLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by dujinkai on 2018/7/24.
 * es商品实体
 */
@Data
public class EsSpu {

    /***
     * 主键id
     */
    private String id;

    /**
     * 商品名称
     */
    private String spuName;

    /**
     * 库存
     */
    private int stock;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 一级分类id
     */
    private long fCateId;

    /**
     * 二级分类id
     */
    private long sCateId;

    /**
     * 三级分类id
     */
    private long tCateId;

    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime = LocalDateTime.now();

}
