package com.djk.brand;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by dujinkai on 2018/7/10.
 * 品牌数据库接口
 */
@Mapper
public interface BrandMapper {

    /**
     * 分页查询品牌信息
     *
     * @param params 查询参数
     * @return 返回品牌信息
     */
    List<Brand> queryBrands(Map<String, Object> params);

    /**
     * 品牌总记录数
     *
     * @param params 查询参数
     * @return 返回品牌总记录数
     */
    int queryBrandsCount(Map<String, Object> params);

    /**
     * 添加品牌
     *
     * @param brand 品牌信息
     * @return 成功返回1 失败返回0
     */
    int addBrand(Brand brand);

    /**
     * 批量删除品牌信息
     *
     * @param ids 品牌id
     * @return 成功》0 失败返回0
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
     * 更新品牌信息
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
