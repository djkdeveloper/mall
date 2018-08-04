package com.djk.spu;

import com.djk.brand.BrandService;
import com.djk.category.CategoryService;
import com.djk.feign.EsService;
import com.djk.feign.EsSpu;
import com.djk.utils.Log;
import com.djk.utils.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    /**
     * 注入es服务接口
     */
    @Autowired
    private EsService esService;

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
        if (spuMapper.addSpu(spu) > 0) {
            esService.addSpuToEs(Arrays.asList(spu.convertToEsSpu()));
            return 1;
        }
        return 0;
    }

    @Log
    @Override
    public int deleteSpus(Long[] ids) {
        if (spuMapper.deleteSpus(ids) > 0) {
            esService.delete(Stream.of(ids).map(String::valueOf).collect(Collectors.toList()));
            return 1;
        }
        return 0;
    }


    @Log
    @Override
    public Spu queryById(long id) {
        return spuMapper.queryById(id);
    }


    @Log
    @Override
    public int updateSpu(Spu spu) {
        if (spuMapper.updateSpu(spu) > 0) {
            esService.update(spu.convertToEsSpu());
            return 1;
        }
        return 0;
    }

    @Transactional
    @Log
    @Override
    public int reduceStock(List<SpuStock> spuStocks) {

        if (CollectionUtils.isEmpty(spuStocks)) {
            log.warn("do not need to reduceStock due to spuStocks is empty...");
            return 0;
        }

        // 库存扣除成功标记
        List<Integer> list = new ArrayList<>();

        spuStocks.stream().forEach(spuStock -> {
            // 库存扣减成功
            if (this.reduceStock(spuStock) > 0) {
                list.add(1);
            }
        });
        return list.size() == spuStocks.size() ? 1 : 0;
    }


    /**
     * 扣除商品库存
     *
     * @param spuStock 商品库存实体
     * @return 成功返回1 失败返回0
     */
    private int reduceStock(SpuStock spuStock) {
        Map<String, Object> params = new HashMap<>();
        params.put("spuId", spuStock.getSpuId());
        params.put("num", spuStock.getNum());
        return spuMapper.reduceStock(params);
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
