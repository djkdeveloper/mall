package com.djk.manager;

import com.djk.utils.PageHelper;

/**
 * Created by dujinkai on 2018/6/30.
 * 管理用户服务接口
 */
public interface ManagerService {

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
     * @param id 用户id
     * @return 返回用户信息
     */
    Manager queryById(long id);

    /**
     * 查询管理员信息和管理员的权限信息
     *
     * @param id 用户id
     * @return 返回用户的权限信息和用户信息
     */
    Manager queryUserAuthority(long id);

    /**
     * 分页查询员工信息
     *
     * @param pageHelper 分页帮助类
     * @param name       员工名称
     * @param managerId  当前登录的员工id
     * @return 返回员工信息
     */
    PageHelper<Manager> queryManagers(PageHelper<Manager> pageHelper, String name, long managerId);

    /**
     * 添加员工信息
     *
     * @param manager 员工信息
     * @return 成功返回1 失败返回0 -1 名称已经存在
     */
    int addManager(Manager manager);

    /**
     * 删除员工信息
     *
     * @param ids 员工id
     * @return 成功返回1 失败返回0
     */
    int deleteManagers(long[] ids, long managerId);

    /**
     * 根据创建人id和员工id查询员工信息
     *
     * @param id        员工id
     * @param managerId 当前登入的人的id
     * @return 返回员工信息
     */
    Manager queryManagerById(long id, long managerId);

    /**
     * 更新员工信息
     *
     * @param manager 员工信息
     * @return 成功返回1  员工名称存在返回-1 失败返回0
     */
    int updateManager(Manager manager);
}
