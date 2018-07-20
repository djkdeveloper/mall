package com.djk.brand;

import com.djk.utils.CustomLocalDateTimeDeserializer;
import com.djk.utils.CustomLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by dujinkai on 2018/7/10.
 * 品牌实体类
 */
@Data
public class Brand {

    /**
     * 主键id
     */
    private long id;

    /**
     * 品牌名称
     */
    private String name;

    /**
     * 删除标记  0 未删除 1 已删除
     */
    private String delFlag;

    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;


    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime modifyTime;

}
