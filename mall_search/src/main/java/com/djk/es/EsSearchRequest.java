package com.djk.es;

import lombok.Data;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Created by dujinkai on 2018/6/11.
 * es请求数据
 */
@Data
public class EsSearchRequest {


    /**
     * 索引名称
     */
    private String indexName = "es_spu_index";

    /**
     * 类型
     */
    private String type = "esSpu";

    /**
     * 查询字符串
     */
    private String queryString;

    /**
     * 是否需要高亮 默认是
     */
    private boolean hightLight = true;

    /**
     * 查询的时候是否需要聚合 默认需要
     */
    private boolean neddAgg = true;

    /**
     * 分页大小
     */
    private int pageSize = 10;

    /**
     * 起始页
     */
    private int pageNum = 0;

    /**
     * 排序
     */
    private List<SortItem> sortItems;

    /**
     * 只显示有货 -1 不过滤
     */
    private int stockFilter = -1;


    /**
     * 价格区间
     */
    private RangeItem<BigDecimal> priceRange;

    /**
     * 属性查询
     */
    private List<AttributeItem> attributes;

    /**
     * 分类id
     */
    private long cateId = -1;

    /**
     * 范围区间
     *
     * @param <T> 范型值
     */
    @Data
    public static class RangeItem<T> {

        /**
         * 最小值
         */
        private T min;

        /**
         * 最大值
         */
        private T max;

        public boolean isValidate() {
            return null != min || null != max;
        }
    }

    /**
     * 属性查询
     */
    @Data
    public static class AttributeItem {
        /**
         * 属性名称
         */
        private String name;

        /**
         * 属性值
         */
        private List<String> values;

        public boolean isValidate() {
            return !StringUtils.isEmpty(name) && !CollectionUtils.isEmpty(values);
        }
    }

    /**
     * 排序，order 0 表示升序；1表示降序
     */
    @Data
    public static class SortItem {
        /**
         * 排序的属性
         */
        private String field;

        /**
         * 排序规则
         */
        private int order;

        public SortOrder getOrder() {
            return order == 1 ? SortOrder.DESC : SortOrder.ASC;
        }

        public boolean isValidate() {
            return order != -1;
        }
    }

    /**
     * 构造NativeSearchQueryBuilder
     *
     * @return 返回NativeSearchQueryBuilder
     */
    public NativeSearchQueryBuilder buildNativeSearchQueryBuilder() {
        return new NativeSearchQueryBuilder().withIndices(this.indexName).withTypes(this.type);
    }


    /**
     * 构造分页数据
     *
     * @return 返回分页
     */
    public Pageable buildPageable() {
        return new PageRequest(this.pageNum, this.pageSize);
    }

    /**
     * 构造bool 复合查询
     *
     * @return 返回bool复合查询
     */
    public BoolQueryBuilder buildBoolQueryBuilder() {

        // bool 查询 不懂自行补脑 https://es.xiaoleilu.com
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        // 产品名称查询
        this.addNameQuery(boolQueryBuilder);

        // 分类查询
        this.addCategoryQuery(boolQueryBuilder);

        // 库存过滤
        this.addStockFilter(boolQueryBuilder);

        // 价格区间过滤
        this.addPriceRange(boolQueryBuilder);

        // 属性查询
        this.addAttributeQuery(boolQueryBuilder);

        return boolQueryBuilder;

    }


    /**
     * 增加属性查询
     *
     * @param boolQueryBuilder bool 查询
     */
    public void addAttributeQuery(BoolQueryBuilder boolQueryBuilder) {
        if (CollectionUtils.isEmpty(this.attributes)) {
            return;
        }

        attributes.stream().forEach(attributeItem -> {
            if (!attributeItem.isValidate()) {
                return;
            }
            boolQueryBuilder.must(QueryBuilders.nestedQuery("esAttributes", QueryBuilders.boolQuery().must(QueryBuilders.termQuery("esAttributes.attributeName", attributeItem.getName())).must(QueryBuilders.termsQuery("esAttributes.attributeValue", attributeItem.getValues())), ScoreMode.Avg));
        });
    }


    /**
     * 添加价格区间的搜索
     *
     * @param boolQueryBuilder bool 查询
     */
    public void addPriceRange(BoolQueryBuilder boolQueryBuilder) {
        if (Objects.isNull(this.priceRange) || !this.priceRange.isValidate()) {
            return;
        }

        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("price");

        if (!StringUtils.isEmpty(this.priceRange.getMin())) {
            rangeQueryBuilder.from(this.priceRange.getMin().doubleValue());
        }


        if (!StringUtils.isEmpty(this.priceRange.getMax())) {
            rangeQueryBuilder.to(this.priceRange.getMax().doubleValue());
        }
        boolQueryBuilder.must(rangeQueryBuilder);
    }

    /**
     * 添加库存过滤查询
     *
     * @param boolQueryBuilder bool 查询
     */
    public void addStockFilter(BoolQueryBuilder boolQueryBuilder) {
        if (stockFilter != -1) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("stock").gt(0));
        }
    }

    /**
     * 添加分类搜索
     *
     * @param boolQueryBuilder boolQueryBuilder bool 查询
     */
    public void addCategoryQuery(BoolQueryBuilder boolQueryBuilder) {
        if (this.cateId > 0) {
            boolQueryBuilder.must(QueryBuilders.multiMatchQuery(this.cateId, new String[]{"fcateId", "scateId", "tcateId"}));
        }
    }


    /**
     * 添加产品名称搜索
     *
     * @param boolQueryBuilder bool 查询
     */
    private void addNameQuery(BoolQueryBuilder boolQueryBuilder) {
        if (StringUtils.isEmpty(this.queryString)) {
            return;
        }

        boolQueryBuilder.must(QueryBuilders.matchQuery("spuName", this.queryString));
    }


    /**
     * 设置排序
     *
     * @param nativeSearchQueryBuilder 查询主体
     */
    public void buildSort(NativeSearchQueryBuilder nativeSearchQueryBuilder) {
        if (CollectionUtils.isEmpty(this.sortItems)) {
            return;
        }

        this.sortItems.stream().forEach(sortItem -> {
            if (!sortItem.isValidate()) {
                return;
            }
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort(sortItem.field).order(sortItem.getOrder()));
        });

    }
}
