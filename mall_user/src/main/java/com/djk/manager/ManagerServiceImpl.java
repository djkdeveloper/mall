package com.djk.manager;

import com.djk.authority.AuthorityService;
import com.djk.role.AdminRoleService;
import com.djk.utils.Log;
import com.djk.utils.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by dujinkai on 2018/6/30.
 * 管理用户服务接口实现
 */
@Service
@Slf4j
public class ManagerServiceImpl implements ManagerService {

    /**
     * 注入数据库接口
     */
    @Autowired
    private ManagerMapper adminUserMapper;

    /**
     * 注入权限接口
     */
    @Autowired
    private AuthorityService authorityService;

    /**
     * 注入角色服务接口
     */
    @Autowired
    private AdminRoleService adminRoleService;

    @Log
    @Override
    public Manager queryByName(String userName) {

        if (StringUtils.isEmpty(userName)) {
            log.error("queryByName fail due to userName is empty....");
            return null;
        }

        return adminUserMapper.queryByName(userName);
    }

    @Log
    @Override
    public Manager queryById(long id) {
        return adminUserMapper.queryById(id);
    }

    @Log
    @Override
    public Manager queryUserAuthority(long id) {
        // 查询用户信息
        Manager manager = this.queryById(id);

        if (Objects.isNull(manager)) {
            log.error("queryUserAuthority fail due to manager is null...");
            return manager;
        }

        // 设置管理员的权限
        manager.cleanPassword().setAuthorities(authorityService.queryManagerAuthoritys(id));
        return manager;
    }

    @Log
    @Override
    public PageHelper<Manager> queryManagers(PageHelper<Manager> pageHelper, String name, long managerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("managerId", managerId);
        return pageHelper.setListDates(setManagerRoles(adminUserMapper.queryManagers(pageHelper.getQueryParams(params, adminUserMapper.queryManagerCount(params)))));
    }


    @Transactional
    @Log
    @Override
    public int addManager(Manager manager) {

        if (Objects.isNull(manager) || Objects.isNull(manager.getManagerRole())) {
            log.error("addManager fail due to manager is empty...");
            return 0;
        }


        // 校验员工名称是否存在
        if (isManagerNameExist(manager.getUserName(), 0)) {
            log.error("addManager fail due to name is exist...");
            return -1;
        }

        // 密码加密
        manager.md5Password();

        // 新增员工
        adminUserMapper.addManager(manager);

        manager.setMangerId();

        // 新增员工和角色的关系
        adminUserMapper.addManagerRole(manager.getManagerRole());

        return 1;
    }

    /**
     * 判断管理员的名称是否存在
     *
     * @param name 管理员名称
     * @param id   管理员id
     * @return 存在返回true  不存在返回false
     */
    private boolean isManagerNameExist(String name, long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("id", id);
        return adminUserMapper.queryCountByName(params) > 0;
    }


    @Override
    public int deleteManagers(long[] ids, long managerId) {
        if (ArrayUtils.isEmpty(ids)) {
            log.error("deleteManagers fail due to ids is empty....");
            return 0;
        }

        Map<String, Object> params = new HashMap<>();
        params.put("ids", ids);
        params.put("managerId", managerId);
        // 删除员工信息
        if (adminUserMapper.deleteManagers(params) > 0) {
            // 删除员工与角色的关系
            adminUserMapper.deleteManagerRoles(ids);

            return 1;
        }

        return 0;
    }

    @Log
    @Override
    public Manager queryManagerById(long id, long managerId) {

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("managerId", managerId);

        Manager manager = adminUserMapper.queryManagerById(params);

        if (Objects.isNull(manager)) {
            return manager;
        }

        // 设置角色
        manager.cleanPassword().setRole(adminRoleService.queryManagerRole(id));

        return manager;
    }

    @Transactional
    @Log
    @Override
    public int updateManager(Manager manager) {
        if (Objects.isNull(manager)) {
            log.error("updateManager fail deu to manager is null....");
            return 0;
        }

        if (isManagerNameExist(manager.getUserName(), manager.getId())) {
            log.error("updateManager fail due to name is exist..");
            return -1;
        }

        // 更新员工信息
        if (adminUserMapper.updateManager(manager) > 0) {

            // 删除员工和角色的关系
            adminUserMapper.deleteManagerRole(manager.getId());

            manager.setMangerId();

            // 新增员工和角色的关系
            adminUserMapper.addManagerRole(manager.getManagerRole());
            return 1;
        }

        return 0;
    }

    /**
     * 设置管理员的角色信息
     *
     * @param managers 管理员
     * @return 返回设置好角色的管理员信息
     */
    private List<Manager> setManagerRoles(List<Manager> managers) {
        managers.stream().forEach(manager -> manager.cleanPassword().setRole(adminRoleService.queryManagerRole(manager.getId())));
        return managers;
    }
}
