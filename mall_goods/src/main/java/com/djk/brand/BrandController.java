package com.djk.brand;

import com.djk.utils.BaseResponse;
import com.djk.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by dujinkai on 2018/7/10.
 * 品牌控制器
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    /**
     * 注入品牌服务接口
     */
    @Autowired
    private BrandService brandService;


    /**
     * 分页查询品牌信息
     *
     * @param pageHelper 分页帮助类
     * @param name       品牌名称
     * @return 返回品牌信息
     */
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('sys.brand.list.post')")
    public BaseResponse queryBrands(PageHelper<Brand> pageHelper, String name) {
        return BaseResponse.build(brandService.queryBrands(pageHelper, name));
    }

    /**
     * 新增品牌信息
     *
     * @param brand 品牌信息
     * @return 成功返回1 失败返回0
     */
    @PostMapping
    @PreAuthorize("hasAuthority('sys.brand.post')")
    public int addBrand(@RequestBody Brand brand) {
        return brandService.addBrand(brand);
    }

    /**
     * 删除品牌信息
     *
     * @param ids 品牌id
     * @return 成功》0 失败返回0
     */
    @DeleteMapping
    @PreAuthorize("hasAuthority('sys.brand.delete')")
    public int deleteBrand(long[] ids) {
        return brandService.deleteBrand(ids);
    }

    /**
     * 根据id查询品牌信息
     *
     * @param id 品牌id
     * @return 返回品牌信息
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('sys.brand.get')")
    public Brand queryBrand(@PathVariable long id) {
        return brandService.queryBrandById(id);
    }

    /**
     * 更新品牌
     *
     * @param brand 品牌信息
     * @return 成功返回》0 失败0
     */
    @PutMapping
    @PreAuthorize("hasAuthority('sys.brand.put')")
    public int updateBrand(@RequestBody Brand brand) {
        return brandService.updatBrand(brand);
    }
}
