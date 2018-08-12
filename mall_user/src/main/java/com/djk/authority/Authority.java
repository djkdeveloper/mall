package com.djk.authority;

import com.djk.utils.CustomLocalDateTimeDeserializer;
import com.djk.utils.CustomLocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    /**
     * 添加子节点
     *
     * @param authority 子权限
     */
    public void addChild(Authority authority) {
        if (Objects.isNull(children)) {
            children = new ArrayList<>();
        }
        children.add(authority);
    }

    /**
     * 构造根节点
     *
     * @return 返回跟节点
     */
    public static Authority buildRootNode() {
        Authority authority = new Authority();
        authority.id = 0;
        authority.name = "全部";
        return authority;
    }

    /**
     * 判断是否是一级节点
     *
     * @return 一级节点返回true  否则返回false
     */
    @JsonIgnore
    public boolean isFirstNode() {
        return this.grade == 1;
    }

    /**
     * 判断是否是二级节点
     *
     * @return 二级节点返回true  否则返回false
     */
    @JsonIgnore
    public boolean isSecondNode() {
        return this.grade == 2;
    }

    /**
     * 判断是否是三级节点
     *
     * @return 三级节点返回true  否则返回false
     */
    @JsonIgnore
    public boolean isThirdNode() {
        return this.grade == 3;
    }

    /**
     * 判断是否是四级节点
     *
     * @return 四级节点返回true  否则返回false
     */
    @JsonIgnore
    public boolean isFourthNode() {
        return this.grade == 4;
    }
}
