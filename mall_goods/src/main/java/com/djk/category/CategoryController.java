package com.djk.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by dujinkai on 2018/7/11.
 * 分类控制器
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    /**
     * 注入分类服务接口
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * 分页查询品牌信息
     *
     * @return 返回品牌信息
     */
    @GetMapping("/list")
    public List<Category> queryCategorys() {
        return categoryService.queryCategorys();
    }

    /**
     * 根据层级查询分类信息
     *
     * @param grade 层级
     * @return 返回该层级的分类
     */
    @GetMapping("/grade/{grade}")
    public List<Category> queryCategoryByGrade(@PathVariable int grade) {
        return categoryService.queryCategoryByGrade(grade);
    }

    /**
     * 新增分类信息
     *
     * @param category 分类信息
     * @return 成功》0 失败= 0
     */
    @PostMapping
    public int addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    /**
     * 根据分类id查询分类信息
     *
     * @param id 分类id
     * @return 返回分类信息
     */
    @GetMapping("/{id}")
    public Category queryCategoryById(@PathVariable long id) {
        return categoryService.queryCategoryById(id);
    }

    /**
     * 更新分类信息
     *
     * @param category 分类信息
     * @return 成功返回1 失败返回0
     */
    @PutMapping
    public int updateCategory(@RequestBody Category category) {
        return categoryService.updateCategory(category);
    }

    /**
     * 删除分类信息
     *
     * @param id 分类id
     * @return 返回-1  下面有子分类不能删除  1 成功  0 失败
     */
    @DeleteMapping("/{id}")
    public int deleteCategory(@PathVariable long id) {
        return categoryService.deleteCategory(id);
    }
}
