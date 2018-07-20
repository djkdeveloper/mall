package com.djk.manager;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by dujinkai on 2018/6/30.
 * 管理用户数据库接口
 */
@Mapper
public interface ManagerMapper {

    /**
     * 根据用户名查询用户信息
     *
     * @param userName 用户名
     * @return 返回用户信息
     */
    Manager queryByName(String userName);

    /**
     * 根据用户id查询用户信息
     *
     * @param id 用户名
     * @return 返回用户信息
     */
    Manager queryById(long id);

    /**
     * 查询管理员总数
     *
     * @param params 查询参数
     * @return 返回管理员总数
     */
    int queryManagerCount(Map<String, Object> params);

    /**
     * 查询管理员信息
     *
     * @param params 查询管理员数量
     * @return 返回管理员数量
     */
    List<Manager> queryManagers(Map<String, Object> params);

    /**
     * 新增员工
     *
     * @param manager 员工信息
     * @return 成功返回1 失败返回0
     */
    int addManager(Manager manager);

    /**
     * 新增员工与角色的关系
     *
     * @param managerRole 员工角色
     * @return 成功返回1 失败返回0
     */
    int addManagerRole(ManagerRole managerRole);

    /**
     * 删除员工信息
     *
     * @param params 删除参数
     * @return 成功返回》0 失败返回0
     */
    int deleteManagers(Map<String, Object> params);

    /**
     * 删除员工与角色的关系
     *
     * @param ids 员工id
     * @return 成功返回》0 失败返回0
     */
    int deleteManagerRoles(long[] ids);

    /**
     * 根据姓名查询记录数
     *
     * @param params 查询参数
     * @return 返回记录数
     */
    int queryCountByName(Map<String, Object> params);

    /**
     * 根据创建人id和员工id查询员工信息
     *
     * @return 返回员工信息
     */
    Manager queryManagerById(Map<String, Object> params);

    /**
     * 更新员工信息
     *
     * @param manager 员工信息
     * @return 成功返回1 失败返回0
     */
    int updateManager(Manager manager);

    /**
     * 根据员工id删除员工和角色的关系
     *
     * @param managerId 员工id
     * @return 成功返回1 失败返回0
     */
    int deleteManagerRole(long managerId);
}
