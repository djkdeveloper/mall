package com.djk.login;

import com.djk.manager.Manager;

/**
 * Created by dujinkai on 2018/6/30.
 * 登录接口
 */
public interface LoginService {

    /**
     * 登录
     *
     * @param manager 登录实体
     * @return AdminLoginResult 登录结果
     */
    AdminLoginResult login(Manager manager);
}
