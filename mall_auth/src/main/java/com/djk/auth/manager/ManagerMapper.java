package com.djk.auth.manager;

import org.apache.ibatis.annotations.Mapper;

/**
 * Created by dujinkai on 2018/8/12.
 * 管理元数据库接口
 */
@Mapper
public interface ManagerMapper {

    /**
     * 根据名称查询管理员信息
     *
     * @param name 名称
     * @return 返回管理员信息
     */
    Manager queryByName(String name);
}
