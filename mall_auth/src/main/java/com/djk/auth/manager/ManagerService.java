package com.djk.auth.manager;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by dujinkai on 2018/8/12.
 * 管理员服务接口
 */
public interface ManagerService extends UserDetailsService {

    /**
     * 根据名称查询管理员信息
     *
     * @param name 管理员名称
     * @return 返回管理员信息
     */
    Manager queryByName(String name);
}
