package com.djk.category;

import com.djk.utils.IteratorUtils;
import com.djk.utils.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dujinkai on 2018/7/11.
 * 分类服务接口
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    /**
     * 注入分类数据库接口
     */
    @Autowired
    private CategoryMapper categoryMapper;

    @Log
    @Override
    public List<Category> queryCategorys() {

        // 所有的分类信息
        List<Category> categories = categoryMapper.queryCategorys();

        if (CollectionUtils.isEmpty(categories)) {
            return categories;
        }

        return getCombinationCategorys(categories);
    }

    @Log
    @Override
    public List<Category> queryCategoryByGrade(int grade) {
        return categoryMapper.queryCategoryByGrade(grade);
    }

    @Log
    @Override
    public int addCategory(Category category) {
        return categoryMapper.addCategory(category);
    }

    @Log
    @Override
    public Category queryCategoryById(long id) {
        return categoryMapper.queryCategoryById(id);
    }

    @Log
    @Override
    public int updateCategory(Category category) {
        return categoryMapper.updateCategory(category);
    }

    @Log
    @Override
    public int deleteCategory(long id) {
        // 判断分类下面是否有子分类 有子分类则不能删除
        if (categoryMapper.queryChildrenNum(id) != 0) {
            log.error("deleteCategory fail due to category has children...");
            return -1;
        }
        return categoryMapper.deleteCategory(id);
    }


    @Log
    @Override
    public List<Category> queryChildren(long id) {
        return categoryMapper.queryChildren(id);
    }

    /**
     * 获得组装后的分类（父分类下面挂子分类）
     *
     * @param categories 所有的分类信息
     * @return 返回组装后的分类信息
     */
    private List<Category> getCombinationCategorys(List<Category> categories) {

        // 过滤出一级分类
        List<Category> firstCategorys = categories.stream().filter(Category::isFirstCategory).collect(Collectors.toList());

        // 过滤出二级分类
        List<Category> secondCategorys = categories.stream().filter(Category::isSecondCategory).collect(Collectors.toList());

        // 过滤出三级分类
        List<Category> thirdCategorys = categories.stream().filter(Category::isThridCategory).collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(firstCategorys) && !CollectionUtils.isEmpty(secondCategorys)) {
            IteratorUtils.zip(firstCategorys, secondCategorys, (category, category2) -> category.getId() == category2.getParentId(), (category1, category21) -> category1.addChild(category21));
        }

        if (!CollectionUtils.isEmpty(secondCategorys) && !CollectionUtils.isEmpty(thirdCategorys)) {
            IteratorUtils.zip(secondCategorys, thirdCategorys, (category, category2) -> category.getId() == category2.getParentId(), (category1, category21) -> category1.addChild(category21));
        }
        return firstCategorys;
    }
}
