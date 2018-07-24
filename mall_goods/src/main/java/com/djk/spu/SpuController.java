package com.djk.spu;

import com.djk.brand.Brand;
import com.djk.brand.BrandService;
import com.djk.category.Category;
import com.djk.category.CategoryService;
import com.djk.utils.BaseResponse;
import com.djk.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by dujinkai on 2018/7/12.
 * 商品控制器
 */
@RestController
@RequestMapping("/spu")
public class SpuController {

    /**
     * 注入商品服务接口
     */
    @Autowired
    private SpuService spuService;

    /**
     * 注入品牌服务接口
     */
    @Autowired
    private BrandService brandService;

    /**
     * 注入分类服务接口
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * 分页查询商品信息
     *
     * @param pageHelper 分页帮助类
     * @param name       商品名称
     * @return 返回商品信息
     */
    @PostMapping("/list")
    public BaseResponse querySpus(PageHelper<Spu> pageHelper, String name) {
        return BaseResponse.build(spuService.querySpus(pageHelper, name));
    }

    /**
     * 查询所有品牌信息
     *
     * @return 返回所有品牌信息
     */
    @GetMapping("/allbrands")
    public List<Brand> queryAllBrands() {
        return brandService.queryAllBrands();
    }

    /**
     * 查询所有一级分类
     *
     * @return 返回所有一级分类
     */
    @GetMapping("/firstcategory")
    public List<Category> queryAllFirstCategorys() {
        return categoryService.queryCategoryByGrade(1);
    }

    /**
     * 添加商品信息
     *
     * @param spu 商品信息
     * @return 成功返回1 失败返回0
     */
    @PostMapping
    public int addSpu(@RequestBody Spu spu) {
        return spuService.addSpu(spu);
    }

    /**
     * 删除商品信息
     *
     * @param ids 商品id集合
     * @return 成功返回》0 失败返回0
     */
    @DeleteMapping
    public int deleteSpus(Long[] ids) {
        return spuService.deleteSpus(ids);
    }

    /**
     * 查询分类下的子分类
     *
     * @param id 分类id
     * @return 返回分类下的子分类
     */
    @GetMapping("/querycaterorychildren/{id}")
    public List<Category> queryCateroryChildren(@PathVariable long id) {
        return categoryService.queryChildren(id);
    }

    /**
     * 根据商品id查询商品信息
     *
     * @param id 商品id
     * @return 返回商品信息
     */
    @GetMapping("/{id}")
    public Spu querySpuById(@PathVariable long id) {
        return spuService.queryById(id);
    }

    /**
     * 更新商品信息
     *
     * @param spu 商品信息
     * @return 成功返回1 失败返回0
     */
    @PutMapping
    public int updateSpu(@RequestBody Spu spu) {
        return spuService.updateSpu(spu);
    }
}
