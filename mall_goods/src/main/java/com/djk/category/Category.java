package com.djk.category;

import com.djk.utils.CustomLocalDateTimeDeserializer;
import com.djk.utils.CustomLocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dujinkai on 2018/7/11.
 * 商品分类实体
 */
@Data
public class Category {

    /**
     * 主键id
     */
    private long id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 父id 如果没有父级 就为0
     */
    private long parentId;

    /**
     * 排序 值越小越靠前
     */
    private int sort;

    /**
     * 层级 1 表示1级 2 表示2级 3表示3级
     */
    private int grade;

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
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime modifyTime;

    /**
     * 该分类下面的子分类
     */
    private List<Category> children;

    /**
     * 新增子分类
     *
     * @param category 子分类
     */
    public void addChild(Category category) {
        if (CollectionUtils.isEmpty(children)) {
            children = new ArrayList<>();
        }

        children.add(category);
    }

    /**
     * 返回子节点的数量
     *
     * @return 返回子节点的数量
     */
    public int getChildNum() {
        return CollectionUtils.isEmpty(this.children) ? 0 : this.children.size();
    }

    /**
     * 判断是否是一级分类 是返回true
     *
     * @return 是一级分类返回ture  否则返回false
     */
    @JsonIgnore
    public boolean isFirstCategory() {
        return 1 == this.grade;
    }

    /**
     * 判断是否是二级分类 是否返回true
     *
     * @return 是二级分类返回true  否则返回false
     */
    @JsonIgnore
    public boolean isSecondCategory() {
        return 2 == this.grade;
    }

    /**
     * 判断是否是三级分类 是返回true
     *
     * @return 是三级分类返回true  否则返回false
     */
    @JsonIgnore
    public boolean isThridCategory() {
        return 3 == this.grade;
    }
}
