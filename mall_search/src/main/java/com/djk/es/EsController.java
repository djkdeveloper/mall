package com.djk.es;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by dujinkai on 2018/7/24.
 * es 控制器
 */
@RestController
@RequestMapping("/es")
public class EsController {

    /**
     * 注入es服务接口
     */
    @Autowired
    private EsService esService;

    /**
     * 将商品加入es中
     *
     * @param esSpus 商品数据
     */
    @PostMapping
    public int addSpuToEs(@RequestBody List<EsSpu> esSpus) {
        esService.addSpuToEs(esSpus);
        return 1;
    }

    /**
     * 搜索商品
     *
     * @param esSearchRequest 搜索请求
     * @return 返回搜索结果
     */
    @PostMapping("/query")
    public EsSearchResponse query(@RequestBody EsSearchRequest esSearchRequest) {
        return esService.query(esSearchRequest);
    }

    /**
     * 删除商品索引
     *
     * @param ids 商品id
     * @return 返回1
     */
    @DeleteMapping
    public int delete(@RequestBody List<String> ids) {
        esService.deleteSpus(ids);
        return 1;
    }


    /**
     * 修改商品信息
     *
     * @param esSpu 商品信息
     */
    @PutMapping
    public int update(@RequestBody EsSpu esSpu) {
        esService.updateSpu(esSpu);
        return 1;
    }
}
