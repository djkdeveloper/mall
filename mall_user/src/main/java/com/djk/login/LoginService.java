package com.djk.login;

import com.djk.customer.Customer;
import com.djk.manager.Manager;

/**
 * Created by dujinkai on 2018/6/30.
 * 登录接口
 */
public interface LoginService {

    /**
     * 后台登录
     *
     * @param manager 登录实体
     * @return AdminLoginResult 登录结果
     */
    AdminLoginResult login(Manager manager);


    /**
     * 前端登录
     *
     * @param customer 登录实体
     * @return 登录结果
     */
    SiteLoginResult login(Customer customer);

}
