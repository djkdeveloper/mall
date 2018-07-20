package com.djk.authority;

import java.util.List;

/**
 * Created by dujinkai on 2018/7/9.
 * 权限服务接口
 */
public interface AuthorityService {

    /**
     * 查询管理员的权限
     *
     * @param managerId 管理员id
     * @return 返回管理员的权限
     */
    List<Authority> queryManagerAuthoritys(long managerId);

    /**
     * 查询管理员的权限（父子归纳好）
     *
     * @param managerId 管理员id
     * @return 返回管理员的权限
     */
    Authority queryManagerAuthoritysInduction(long managerId);


    /**
     * 查询角色下面的权限信息
     *
     * @param roleId 角色id
     * @return 返回角色下面的权限
     */
    List<Authority> queryByRoleId(long roleId);

}
