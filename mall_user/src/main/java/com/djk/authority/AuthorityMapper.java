package com.djk.authority;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by dujinkai on 2018/7/10.
 * 权限数据库接口
 */
@Mapper
public interface AuthorityMapper {

    /**
     * 查询管理员的权限
     *
     * @param managerId 管理员id
     * @return 返回管理员的权限
     */
    List<Authority> queryManagerAuthoritys(long managerId);

    /**
     * 根据角色id查询权限
     *
     * @param roleId 角色id
     * @return 返回权限
     */
    List<Authority> queryByRoleId(long roleId);
}
