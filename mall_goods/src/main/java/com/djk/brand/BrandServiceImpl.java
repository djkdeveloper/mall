package com.djk.brand;

import com.djk.utils.Log;
import com.djk.utils.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by dujinkai on 2018/7/10.
 * 品牌服务接口实现
 */
@Service
@Slf4j
public class BrandServiceImpl implements BrandService {

    /**
     * 注入品牌数据库接口
     */
    @Autowired
    private BrandMapper brandMapper;

    @Log
    @Override
    public PageHelper<Brand> queryBrands(PageHelper<Brand> pageHelper, String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return pageHelper.setListDates(brandMapper.queryBrands(pageHelper.getQueryParams(params, brandMapper.queryBrandsCount(params))));
    }

    @Log
    @Override
    public int addBrand(Brand brand) {

        if (Objects.isNull(brand)) {
            log.error("addBrand fail due to brand is null...");
            return 0;
        }
        return brandMapper.addBrand(brand);
    }

    @Log
    @Override
    public int deleteBrand(long[] ids) {

        if (ArrayUtils.isEmpty(ids)) {
            log.error("deleteBrand fail due to ids is null...");
            return 0;
        }
        return brandMapper.deleteBrand(ids);
    }

    @Log
    @Override
    public Brand queryBrandById(long id) {
        return brandMapper.queryBrandById(id);
    }

    @Log
    @Override
    public int updatBrand(Brand brand) {

        if (Objects.isNull(brand)) {
            log.error("updatBrand fail ...due to brand is null...");
            return 0;
        }
        return brandMapper.updatBrand(brand);
    }

    @Log
    @Override
    public List<Brand> queryAllBrands() {
        return brandMapper.queryAllBrands();
    }
}
