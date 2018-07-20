package com.djk.role;

import com.djk.utils.PageHelper;

import java.util.List;

/**
 * Created by dujinkai on 2018/7/9.
 * 管理员角色服务接口
 */
public interface AdminRoleService {

    /**
     * 分页查询角色信息
     *
     * @param pageHelper 分页帮助类
     * @param name       角色名称
     * @param managerId  当前登录的员工id
     * @return 返回角色信息
     */
    PageHelper<AdminRole> queryRoles(PageHelper<AdminRole> pageHelper, String name, long managerId);

    /**
     * 新增角色
     *
     * @param adminRole 角色信息
     * @return 成功返回 1 失败返回0
     */
    int addRoles(AdminRole adminRole);

    /**
     * 删除角色
     *
     * @param ids       角色id
     * @param managerId 当前登录的员工id
     * @return 成功返回》0 失败返回0 -1 角色被员工使用不能删除
     */
    int deleteRoles(long[] ids, long managerId);

    /**
     * 查询管理员的角色信息
     *
     * @param managerId 管理员id
     * @return 返回管理员的角色信息
     */
    AdminRole queryManagerRole(long managerId);

    /**
     * 查询员工创建的角色
     *
     * @param managerId 员工id
     * @return 返回员工创建的角色
     */
    List<AdminRole> queryManagerCreatedRoles(long managerId);

    /**
     * 根据id查询角色信息
     *
     * @param id        角色id
     * @param managerId 当前管理员id
     * @return 返回角色新
     */
    AdminRole queryRoleById(long id, long managerId);

    /**
     * 更新角色信息
     *
     * @param adminRole 角色嘻嘻
     * @return 成功返回1 失败返回0
     */
    int updateRole(AdminRole adminRole);
}
