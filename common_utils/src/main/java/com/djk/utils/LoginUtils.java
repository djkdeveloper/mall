package com.djk.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dujinkai on 2018/7/2.
 * 登录工具
 */
public class LoginUtils {

    private static LoginUtils ourInstance = new LoginUtils();

    public static LoginUtils getInstance() {
        return ourInstance;
    }

    private LoginUtils() {
    }

    /**
     * 获取登录的用户id
     *
     * @return 返回登录的用户id
     */
    public long getUserId(HttpServletRequest request) {
        return Long.parseLong((request.getAttribute("userId")).toString());
    }

}
