package com.djk.brand;

import com.djk.utils.PageHelper;

import java.util.List;


/**
 * Created by dujinkai on 2018/7/10.
 * 品牌服务接口
 */
public interface BrandService {

    /**
     * 分页查询品牌信息
     *
     * @param pageHelper 分页帮助类
     * @param name       品牌名称
     * @return 返回品牌信息
     */
    PageHelper<Brand> queryBrands(PageHelper<Brand> pageHelper, String name);

    /**
     * 添加品牌
     *
     * @param brand 品牌信息
     * @return 成功返回1 失败返回0
     */
    int addBrand(Brand brand);

    /**
     * 删除品牌
     *
     * @param ids 品牌id集合
     * @return 成功》0 失败=0
     */
    int deleteBrand(long[] ids);

    /**
     * 根据id查询品牌信息
     *
     * @param id 品牌id
     * @return 返回品牌信息
     */
    Brand queryBrandById(long id);

    /**
     * 更新品牌
     *
     * @param brand 品牌信息
     * @return 成功返回1 失败返回0
     */
    int updatBrand(Brand brand);

    /**
     * 查询所有的品牌信息
     *
     * @return 返回所有的品牌信息
     */
    List<Brand> queryAllBrands();
}
