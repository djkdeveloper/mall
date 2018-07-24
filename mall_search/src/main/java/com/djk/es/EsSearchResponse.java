package com.djk.es;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.bucket.nested.InternalNested;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.springframework.data.elasticsearch.core.ResultsMapper;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by dujinkai on 2018/6/11.
 * 搜索结果
 */
@Data
public class EsSearchResponse {

    /**
     * 总记录数
     */
    private int total;

    /**
     * 记录数
     */
    private List<EsSpu> datas;

    /**
     * 聚合后的数据 主要包含品牌和属性
     */
    private Map<String, List<String>> aggRes = new HashMap<>();


    /**
     * 构造es返回数据
     *
     * @param searchResponse es搜索结果
     * @param resultsMapper  结果转化器
     * @return 返回es数据
     */
    public static EsSearchResponse buildEsSearchResponse(SearchResponse searchResponse, ResultsMapper resultsMapper) {
        EsSearchResponse esSearchResponse = new EsSearchResponse();
        esSearchResponse.total = (int) searchResponse.getHits().getTotalHits();
        esSearchResponse.datas = resultsMapper.mapResults(searchResponse, EsSpu.class, null).getContent();
        esSearchResponse.aggRes = getAggResult(searchResponse);

        return esSearchResponse;
    }

    /***
     * 返回聚合的结果
     *
     * @param searchResponse es搜索结果
     * @return 返回聚合结果
     */
    @JsonIgnore
    private static Map<String, List<String>> getAggResult(SearchResponse searchResponse) {

        Map<String, List<String>> map = new HashMap<>();

        if (Objects.isNull(searchResponse.getAggregations())) {
            return map;
        }

        map.putAll(getAttributes(searchResponse.getAggregations().get("esAttributes")));
        return map;
    }

    /**
     * 获得StringTerms的值的集合
     *
     * @param aggregation 聚合
     * @return 返回StringTerms的值的集合
     */
    private static List<String> getStringTerms(Aggregation aggregation) {
        if (Objects.isNull(aggregation)) {
            return Collections.emptyList();
        }

        StringTerms stringTerms = (StringTerms) aggregation;

        if (CollectionUtils.isEmpty(stringTerms.getBuckets())) {
            return Collections.emptyList();
        }

        return stringTerms.getBuckets().stream().map(key -> (String) key.getKey()).collect(Collectors.toList());
    }

    /**
     * 获得属性的聚合
     *
     * @param aggregation 属性的聚合
     * @return 返回属性的聚合
     */
    private static Map<String, List<String>> getAttributes(Aggregation aggregation) {
        Map<String, List<String>> attributes = new HashMap<>();

        if (Objects.isNull(aggregation)) {
            return attributes;
        }

        InternalNested nested = (InternalNested) aggregation;
        if (CollectionUtils.isEmpty(nested.getAggregations().asList())) {
            return attributes;
        }
        StringTerms stringTerms = (StringTerms) nested.getAggregations().asList().get(0);
        stringTerms.getBuckets().stream().forEach(bucket -> attributes.put((String) bucket.getKey(), getStringTerms(bucket.getAggregations().asList().get(0))));
        return attributes;
    }
}
