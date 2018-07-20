package com.djk.spu;

import com.djk.brand.BrandService;
import com.djk.category.CategoryService;
import com.djk.utils.Log;
import com.djk.utils.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by dujinkai on 2018/7/12.
 * 商品服务接口实现
 */
@Service
@Slf4j
public class SpuServiceImpl implements SpuService {

    /**
     * 注入商品数据库接口
     */
    @Autowired
    private SpuMapper spuMapper;

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

    @Log
    @Override
    public PageHelper<Spu> querySpus(PageHelper<Spu> pageHelper, String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return pageHelper.setListDates(getSpusWithCateAndBrand(spuMapper.querySpus(pageHelper.getQueryParams(params, spuMapper.querySpuCount(params)))));
    }


    @Log
    @Override
    public int addSpu(Spu spu) {
        return spuMapper.addSpu(spu);
    }

    @Log
    @Override
    public int deleteSpus(long[] ids) {
        return spuMapper.deleteSpus(ids);
    }


    @Log
    @Override
    public Spu queryById(long id) {
        return spuMapper.queryById(id);
    }


    @Log
    @Override
    public int updateSpu(Spu spu) {
        return spuMapper.updateSpu(spu);
    }

    /**
     * 获得有三级分类和品牌的商品信息
     *
     * @param spus 商品信息
     * @return 返回有三级分类和品牌的商品信息
     */
    @Log
    private List<Spu> getSpusWithCateAndBrand(List<Spu> spus) {
        if (CollectionUtils.isEmpty(spus)) {
            log.warn(" no need to set cate and brand ....due to spus is empty.....");
            return spus;
        }

        return spus.stream().map(spu -> {
            spu.setBrand(brandService.queryBrandById(spu.getBrandId()));
            spu.setThirdCategory(categoryService.queryCategoryById(spu.getThirdCateId()));
            return spu;
        }).collect(Collectors.toList());
    }
}
