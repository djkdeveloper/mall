package com.djk.utils;

import lombok.Data;

import java.util.List;

/**
 * Created by dujinkai on 17/5/9.
 * 分页基础响应类
 */
@Data
public class BaseResponse {


    private BaseResponse() {

    }


    private BaseResponse(PageHelper pageHelper) {
        this.data = pageHelper.getList();
        this.recordsTotal = pageHelper.getRows();
        this.recordsFiltered = pageHelper.getRows();
        this.totalPage = pageHelper.getTotalPages();
    }

    /**
     * 构造分页响应实体
     *
     * @param pageHelper 分页帮助类
     * @return 返回分页响应实体
     */
    public static BaseResponse build(PageHelper pageHelper) {
        return new BaseResponse(pageHelper);
    }

    /**
     * 返回的数据
     */
    private List data;

    /**
     * 总记录数
     */
    private int recordsTotal;

    /**
     * 过滤后的记录数
     */
    private int recordsFiltered;

    /**
     * 总的页数
     */
    private int totalPage;
}
