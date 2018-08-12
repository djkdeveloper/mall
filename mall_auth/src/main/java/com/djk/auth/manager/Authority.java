package com.djk.auth.manager;

import com.djk.utils.CustomLocalDateTimeDeserializer;
import com.djk.utils.CustomLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by dujinkai on 2018/7/9.
 * 权限实体
 */
@Data
public class Authority {

    /**
     * 主键id
     */
    private int id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 父级id 如果是第一级  则为0
     */
    private int parentId;

    /**
     * 级别 1 表示第一级 2表示第二级
     */
    private int grade;

    /**
     * 菜单权限code
     */
    private String code;

    /**
     * 权限
     */
    private String authority;

    /**
     * 字节点
     */
    private List<Authority> children;


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
}
