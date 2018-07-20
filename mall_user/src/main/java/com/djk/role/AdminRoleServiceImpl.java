package com.djk.role;

import com.djk.authority.AuthorityService;
import com.djk.utils.Log;
import com.djk.utils.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by dujinkai on 2018/7/9.
 * 管理员角色服务接口实现
 */
@Slf4j
@Service
public class AdminRoleServiceImpl implements AdminRoleService {


    /**
     * 注入角色数据库接口
     */
    @Autowired
    private AdminRoleMapper adminRoleMapper;

    /**
     * 注入权限接口
     */
    @Autowired
    private AuthorityService authorityService;

    @Log
    @Override
    public PageHelper<AdminRole> queryRoles(PageHelper<AdminRole> pageHelper, String name, long managerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("managerId", managerId);
        return pageHelper.setListDates(adminRoleMapper.queryRoles(pageHelper.getQueryParams(params, adminRoleMapper.queryRolesCount(params))));
    }

    @Transactional
    @Log
    @Override
    public int addRoles(AdminRole adminRole) {

        // 角色或者权限不存在 直接返回失败
        if (Objects.isNull(adminRole) || CollectionUtils.isEmpty(adminRole.getRoleAuthorities())) {
            log.error("addRoles fail due to role or authoritys is not exist....");
            return 0;
        }

        // 新增角色
        adminRoleMapper.addRole(adminRole);

        // 设置roleid
        adminRole.setRoleId();

        // 新增权限
        adminRoleMapper.addRoleAuthoritys(adminRole.getRoleAuthorities());

        return 1;
    }

    @Transactional
    @Log
    @Override
    public int deleteRoles(long[] ids, long managerId) {
        if (ArrayUtils.isEmpty(ids)) {
            log.error("do not need to deleteRoles due to ids is empty...");
            return 0;
        }

        // 如果角色有关联的员工 则不能删除
        if (isRoleHasManager(ids)) {
            log.error("deleteRoles fail due to role has manager");
            return -1;
        }

        Map<String, Object> params = new HashMap<>();

        params.put("managerId", managerId);
        params.put("ids", ids);
        // 删除角色
        if (adminRoleMapper.deleteRoles(params) > 0) {
            // 删除角色和权限的关联关系
            adminRoleMapper.deleteRoleAuthoritys(ids);

            return 1;
        }
        return 0;
    }

    @Log
    @Override
    public AdminRole queryManagerRole(long managerId) {
        return adminRoleMapper.queryManagerRole(managerId);
    }

    @Log
    @Override
    public List<AdminRole> queryManagerCreatedRoles(long managerId) {
        return adminRoleMapper.queryManagerCreatedRoles(managerId);
    }

    @Log
    @Override
    public AdminRole queryRoleById(long id, long managerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("managerId", managerId);
        AdminRole adminRole = adminRoleMapper.queryRoleById(params);
        if (Objects.isNull(adminRole)) {
            return adminRole;
        }
        // 设置角色的权限
        adminRole.setAuthorities(authorityService.queryByRoleId(adminRole.getId()));
        return adminRole;
    }

    @Transactional
    @Log
    @Override
    public int updateRole(AdminRole adminRole) {
        if (Objects.isNull(adminRole)) {
            log.error("updateRole fail due to adminRole is null...");
            return 0;
        }

        // 更新角色
        if (adminRoleMapper.updateRole(adminRole) > 0) {

            // 删除角色和权限的关系
            adminRoleMapper.deleteRoleAuthoritys(new long[]{adminRole.getId()});

            // 设置roleid
            adminRole.setRoleId();

            // 新增角色和权限的关系
            adminRoleMapper.addRoleAuthoritys(adminRole.getRoleAuthorities());

            return 1;
        }

        return 0;
    }

    /**
     * 判断角色是否有员工关联
     *
     * @param ids 角色id
     * @return 有返回 true 没有返回false
     */
    private boolean isRoleHasManager(long[] ids) {
        return adminRoleMapper.roleManagerCount(ids) > 0;
    }
}
