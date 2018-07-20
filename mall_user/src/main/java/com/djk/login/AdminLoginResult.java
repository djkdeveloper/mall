package com.djk.login;

import lombok.Data;

/**
 * Created by dujinkai on 2018/6/30.
 * 登录结果实体
 */
@Data
public class AdminLoginResult {

    /**
     * 登录接口 0 成功 -1 失败
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
    public static AdminLoginResult build() {
        return new AdminLoginResult();
    }

    /**
     * 构造登录失败的实体
     *
     * @return 返回登录失败实体
     */
    public static AdminLoginResult buildLoginFail() {
        AdminLoginResult adminLoginResult = build();
        adminLoginResult.result = -1;
        return adminLoginResult;
    }

    /**
     * 构造登录成功实体
     *
     * @param token token
     * @return 返回登录成功实体
     */
    public static AdminLoginResult buildLoginSuccess(String token) {
        AdminLoginResult adminLoginResult = build();
        adminLoginResult.result = 0;
        adminLoginResult.token = token;
        return adminLoginResult;
    }
}
