package com.djk.es;

import com.djk.utils.CustomLocalDateTimeDeserializer;
import com.djk.utils.CustomLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by dujinkai on 2018/7/24.
 * es商品实体
 */
@Data
@Document(indexName = "es_spu_index", type = "esSpu")
public class EsSpu {

    /***
     * 主键id
     */
    @Id
    @Field(type = FieldType.Keyword, store = true)
    private String id;

    /**
     * 商品名称
     */
    @Field(analyzer = "ik_max_word", searchAnalyzer = "ik_smart", type = FieldType.Text, store = true)
    private String spuName;


    /**
     * 库存
     */
    @Field(type = FieldType.Integer)
    private int stock;

    /**
     * 价格
     */
    @Field(type = FieldType.Double)
    private BigDecimal price;

    /**
     * 一级分类id
     */
    @Field(type = FieldType.Long)
    private long fCateId;

    /**
     * 二级分类id
     */
    @Field(type = FieldType.Long)
    private long sCateId;

    /**
     * 三级分类id
     */
    @Field(type = FieldType.Long)
    private long tCateId;


    /**
     * 图片地址
     */
    @Field(type = FieldType.Keyword)
    private String pic;

    /**
     * 创建时间
     */
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime = LocalDateTime.now();

    /**
     * 根据id构造es商品对象
     *
     * @param id 主键id
     * @return 返回es商品对象
     */
    public static EsSpu buildById(String id) {
        EsSpu esProduct = new EsSpu();
        esProduct.id = id;
        return esProduct;
    }

}
