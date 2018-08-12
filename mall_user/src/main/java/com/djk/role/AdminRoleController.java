package com.djk.role;

import com.djk.authority.Authority;
import com.djk.authority.AuthorityService;
import com.djk.manager.Manager;
import com.djk.manager.ManagerService;
import com.djk.utils.BaseResponse;
import com.djk.utils.LoginUtils;
import com.djk.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Created by dujinkai on 2018/7/15.
 * 角色控制器
 */
@RestController
@RequestMapping("/role")
public class AdminRoleController {

    /**
     * 注入角色服务接口
     */
    @Autowired
    private AdminRoleService adminRoleService;

    /**
     * 注入权限服务接口
     */
    @Autowired
    private AuthorityService authorityService;

    /**
     * 注入管理服务接口
     */
    @Autowired
    private ManagerService managerService;


    /**
     * 分页查询角色信息
     *
     * @param pageHelper 分页帮助类
     * @param name       角色名称
     * @return 返回查询信息
     */
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('sys.role.list.post')")
    public BaseResponse queryRoles(PageHelper<AdminRole> pageHelper, String name, Authentication authentication) {
        return BaseResponse.build(adminRoleService.queryRoles(pageHelper, name, getUserId(authentication)));
    }


    /**
     * 查询当前角色的权限
     *
     * @return 返回当前角色的权限
     */
    @GetMapping("/authoritiesinduction")
    @PreAuthorize("hasAuthority('sys.role.authoritiesinduction.get')")
    public Authority queryAuthoritiesInduction(Authentication authentication) {
        return authorityService.queryManagerAuthoritysInduction(getUserId(authentication));
    }

    /**
     * 新增角色
     *
     * @param adminRole 角色信息
     * @return 成功》0 失败返回0
     */
    @PostMapping
    @PreAuthorize("hasAuthority('sys.role.post')")
    public int addRole(@RequestBody AdminRole adminRole, Authentication authentication) {
        return adminRoleService.addRoles(adminRole.addCreator(getUserId(authentication)));
    }

    /**
     * 删除角色
     *
     * @param ids 角色id
     * @return 成功返回》0 失败返回0 -1 角色被员工使用不能删除
     */
    @DeleteMapping
    @PreAuthorize("hasAuthority('sys.role.delete')")
    public int deleteRoles(long[] ids, Authentication authentication) {
        return adminRoleService.deleteRoles(ids, getUserId(authentication));
    }

    /**
     * 根据id查询角色信息
     *
     * @param id 角色id
     * @return 返回角色信息
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('sys.role.get')")
    public AdminRole queryById(@PathVariable long id, Authentication authentication) {
        return adminRoleService.queryRoleById(id, getUserId(authentication));
    }

    /**
     * 更新角色信息
     *
     * @param adminRole 角色信息
     * @return 成功返回1 失败返回0
     */
    @PutMapping
    @PreAuthorize("hasAuthority('sys.role.put')")
    public int updateRole(@RequestBody AdminRole adminRole, Authentication authentication) {
        adminRole.setCreator(getUserId(authentication));
        return adminRoleService.updateRole(adminRole);
    }

    /**
     * 获得管理员id
     *
     * @param authentication 认证信息
     * @return 返回管理员id
     */
    private long getUserId(Authentication authentication) {
        Manager manager = managerService.queryByName(authentication.getName());
        return Objects.nonNull(manager) ? manager.getId() : 0;
    }
}
