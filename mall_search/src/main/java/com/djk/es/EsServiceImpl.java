package com.djk.es;

import com.djk.utils.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.DefaultResultMapper;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsMapper;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by dujinkai on 2018/7/24.
 * es服务接口实现
 */
@Slf4j
@Service
public class EsServiceImpl implements EsService, InitializingBean {

    /**
     * 注入es数据库接口
     */
    @Autowired
    private EsMapper esMapper;

    /**
     * 注入es模版
     */
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 结果转化器
     */
    private ResultsMapper resultsMapper;

    @Log
    @Override
    public void addSpuToEs(List<EsSpu> esSpus) {
        if (CollectionUtils.isEmpty(esSpus)) {
            log.warn("addSpuToEs fail due to esSpus is null...");
            return;
        }

        // 将商品放入es中
        esMapper.saveAll(esSpus);
    }

    @Log
    @Override
    public EsSearchResponse query(EsSearchRequest esSearchRequest) {

        if (Objects.isNull(esSearchRequest)) {
            log.error("esSearchRequest is empty....");
            return new EsSearchResponse();
        }

        // 建立dsl搜索语句
        SearchQuery searchQuery = buildSearchQuery(esSearchRequest);

        log.info("search from Elasticsearch and DSL :{}", searchQuery.getQuery().toString());

        return elasticsearchTemplate.query(searchQuery, searchResponse -> {
            log.debug("result:" + searchResponse);
            return EsSearchResponse.buildEsSearchResponse(searchResponse, resultsMapper);
        });
    }

    @Log
    @Override
    public void deleteSpus(List<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            log.warn("not to need deleteSpus due to ids is empty...");
            return;
        }
        log.error("deleteids {}", ids);

        esMapper.deleteAll(ids.stream().map(EsSpu::buildById).collect(Collectors.toList()));

    }

    @Log
    @Override
    public void updateSpu(EsSpu esSpu) {
        if (Objects.isNull(esSpu)) {
            log.warn("not to need updateSpu due to esSpu is empty....");
            return;
        }

        esMapper.save(esSpu);
    }

    /**
     * 构造查询语句
     *
     * @param esSearchRequest 查询请求
     * @return 返回查询语句
     */
    private SearchQuery buildSearchQuery(EsSearchRequest esSearchRequest) {
        //设置查询主体
        NativeSearchQueryBuilder nativeSearchQueryBuilder = esSearchRequest.buildNativeSearchQueryBuilder().withPageable(esSearchRequest.buildPageable()).withQuery(esSearchRequest.buildBoolQueryBuilder());

        // 设置排序
        addSort(nativeSearchQueryBuilder, esSearchRequest);

        // 聚合
        if (esSearchRequest.isNeddAgg()) {
            // 增加聚合
            addAttributeAggregation(nativeSearchQueryBuilder);
        }


        if (esSearchRequest.isHightLight()) {
            // 设置高亮
            setHighlighted(nativeSearchQueryBuilder);
        }

        return nativeSearchQueryBuilder.build();
    }

    /**
     * 设置高亮
     *
     * @param nativeSearchQueryBuilder 查询主体
     */
    private void setHighlighted(NativeSearchQueryBuilder nativeSearchQueryBuilder) {
        nativeSearchQueryBuilder.withHighlightFields(new HighlightBuilder.Field("spuName").preTags("<span class='red'>").postTags("</span>"));
    }


    /**
     * 增加属性的聚合
     *
     * @param nativeSearchQueryBuilder 查询主体
     */
    private void addAttributeAggregation(NativeSearchQueryBuilder nativeSearchQueryBuilder) {
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.nested("esAttributes", "esAttributes").subAggregation(AggregationBuilders.terms("attributeName").field("esAttributes.attributeName").subAggregation(AggregationBuilders.terms("attributeValue").field("esAttributes.attributeValue"))));
    }

    /**
     * 添加排序
     *
     * @param nativeSearchQueryBuilder 查询主题
     * @param request                  查询参数
     */
    private void addSort(NativeSearchQueryBuilder nativeSearchQueryBuilder, EsSearchRequest request) {
        request.buildSort(nativeSearchQueryBuilder);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        resultsMapper = new DefaultResultMapper(elasticsearchTemplate.getElasticsearchConverter().getMappingContext());
    }
}
