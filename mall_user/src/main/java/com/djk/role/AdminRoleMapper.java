package com.djk.role;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by dujinkai on 2018/7/15.
 * 角色数据库接口
 */
@Mapper
public interface AdminRoleMapper {

    /**
     * 查询角色总数
     *
     * @param params 查询参数
     * @return 返回角色总数
     */
    int queryRolesCount(Map<String, Object> params);


    /**
     * 分页查询角色信息
     *
     * @param params 查询参数
     * @return 返回角色信息
     */
    List<AdminRole> queryRoles(Map<String, Object> params);

    /**
     * 新增角色
     *
     * @param adminRole 角色
     * @return 成功返回1 失败返回0
     */
    int addRole(AdminRole adminRole);

    /**
     * 新增角色和权限的关系
     *
     * @param roleAuthorities 角色和权限的关系
     * @return 成功返回》1 失败返回0
     */
    int addRoleAuthoritys(List<RoleAuthority> roleAuthorities);

    /**
     * 删除角色
     *
     * @param ids 角色id
     * @return 成功返回》0 失败返回0
     */
    int deleteRoles(Map<String, Object> params);

    /**
     * 删除角色和权限的关系
     *
     * @param ids 角色id
     * @return 成功返回》0 失败返回0
     */
    int deleteRoleAuthoritys(long[] ids);

    /**
     * 根据管理员id查询角色信息
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
     * 角色关联的员工数量
     *
     * @param ids 角色id
     * @return 返回角色关联的员工数量
     */
    int roleManagerCount(long[] ids);

    /**
     * 根据角色id查询角色信息
     *
     * @param params 查询参数
     * @return 返回角色信息
     */
    AdminRole queryRoleById(Map<String, Object> params);

    /**
     * 更新角色信息
     *
     * @param adminRole 角色信息
     * @return 成功返回1 失败返回0
     */
    int updateRole(AdminRole adminRole);

}
