package com.djk.login;

import lombok.Data;

/**
 * Created by dujinkai on 2018/7/27.
 * 前端登录结果实体
 */
@Data
public class SiteLoginResult {

    /**
     * 登录结果 0 成功 -1 失败
     */
    private int result;

    /**
     * 认证的token
     */
    private String token;

    /**
     * 构造实体
     *
     * @return 返回实体
     */
    public static SiteLoginResult build() {
        return new SiteLoginResult();
    }

    /**
     * 构造登录失败的实体
     *
     * @return 返回登录失败实体
     */
    public static SiteLoginResult buildLoginFail() {
        SiteLoginResult siteLoginResult = build();
        siteLoginResult.result = -1;
        return siteLoginResult;
    }

    /**
     * 构造登录成功实体
     *
     * @param token token
     * @return 返回登录成功实体
     */
    public static SiteLoginResult buildLoginSuccess(String token) {
        SiteLoginResult siteLoginResult = build();
        siteLoginResult.result = 0;
        siteLoginResult.token = token;
        return siteLoginResult;
    }
}
