package com.djk.auth.manager;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by dujinkai on 2018/8/12.
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
}
