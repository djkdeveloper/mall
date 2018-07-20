package com.djk.category;


import java.util.List;

/**
 * Created by dujinkai on 2018/7/11.
 * 分类服务接口
 */
public interface CategoryService {

    /**
     * 查询分类信息 （包含分类下的子分类）
     *
     * @return 返回分类信息
     */
    List queryCategorys();

    /**
     * 根据层级查询分类
     *
     * @param grade 层级
     * @return 返回层级对应的分类
     */
    List<Category> queryCategoryByGrade(int grade);

    /**
     * 新增分类
     *
     * @param category 分类信息
     * @return 成功》0 失败= 0
     */
    int addCategory(Category category);

    /**
     * 根据id查询分类信息
     *
     * @param id 分类id
     * @return 返回分类信息
     */
    Category queryCategoryById(long id);

    /**
     * 根据分类信息
     *
     * @param category 分类信息
     * @return 成功返回1 失败返回0
     */
    int updateCategory(Category category);

    /**
     * 删除分类信息(分类下面有子分类不能删除)
     *
     * @param id 分类ID
     * @return 返回-1  下面有子分类不能删除  1 成功  0 失败
     */
    int deleteCategory(long id);


    /**
     * 查询分类下的所有子分类
     *
     * @param id 分类id
     * @return 返回分类下的所有子分类
     */
    List<Category> queryChildren(long id);
}
