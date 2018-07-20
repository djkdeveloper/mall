package com.djk.category;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by dujinkai on 2018/7/11.
 * 分类数据接口
 */
@Mapper
public interface CategoryMapper {

    /**
     * 查询所有分类信息
     *
     * @return 返回分类信息
     */
    List<Category> queryCategorys();

    /**
     * 根据层级查询分类
     *
     * @param grade 层级
     * @return 返回层级对应的分类
     */
    List<Category> queryCategoryByGrade(int grade);

    /**
     * 新增分类信息
     *
     * @param category 分类信息
     * @return 成功》0 失败= 0
     */
    int addCategory(Category category);

    /**
     * 根据分类id查询分类信息
     *
     * @param id 分类id
     * @return 返回分类信息
     */
    Category queryCategoryById(long id);

    /**
     * 更新分类信息
     *
     * @param category 分类信息
     * @return 成功返回1 失败返回0
     */
    int updateCategory(Category category);

    /**
     * 删除分类信息
     *
     * @param id 分类id
     * @return 成功返回1 失败返回0
     */
    int deleteCategory(long id);

    /**
     * 查询分类子分类的数量
     *
     * @param id 分类id
     * @return 返回分类下面子分类的数量
     */
    int queryChildrenNum(long id);

    /**
     * 查询分类下的子分类
     *
     * @param id 分类id
     * @return 返回分类下的子分类
     */
    List<Category> queryChildren(long id);
}
