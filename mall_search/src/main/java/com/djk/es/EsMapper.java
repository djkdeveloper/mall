package com.djk.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by dujinkai on 2018/7/24.
 * es 数据库接口
 */
public interface EsMapper extends ElasticsearchRepository<EsSpu, String> {
}