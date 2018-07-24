package com.djk.es;

import java.util.List;

/**
 * Created by dujinkai on 2018/7/24.
 * es搜索服务
 */
public interface EsService {

    /**
     * 将商品放入es中
     *
     * @param esSpus 商品信息
     */
    void addSpuToEs(List<EsSpu> esSpus);

    /**
     * 搜索商品
     *
     * @param esSearchRequest 搜索请求
     * @return 返回产品信息
     */
    EsSearchResponse query(EsSearchRequest esSearchRequest);

    /**
     * 根据商品id删除产品索引
     *
     * @param ids 商品id
     */
    void deleteSpus(List<String> ids);

    /**
     * 修改商品信息
     *
     * @param esSpu 商品信息
     */
    void updateSpu(EsSpu esSpu);
}
