package com.djk.manager;

import com.djk.role.AdminRole;
import com.djk.role.AdminRoleService;
import com.djk.utils.BaseResponse;
import com.djk.utils.LoginUtils;
import com.djk.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

/**
 * Created by dujinkai on 2018/7/2.
 * 管理员控制器
 */
@RestController
@RequestMapping("/manager")
public class ManagerController {

    /**
     * 注入用户服务接口
     */
    @Autowired
    private ManagerService adminUserService;


    /**
     * 注入角色服务接口
     */
    @Autowired
    private AdminRoleService adminRoleService;


    /**
     * 查询当前登录的用户信息
     *
     * @return 返回当前登录的用户信息
     */
    @GetMapping("/current")
    public Authentication queryCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 查询用户的权限信息
     *
     * @return 返回用户信息和权限信息
     */
    @GetMapping("/managerauthoritys")
    public Manager queryManagerAuthoritys(Authentication authentication) {
        return adminUserService.queryUserAuthority(authentication.getName());
    }

    /**
     * 分页查询员工信息
     *
     * @param pageHelper 分页帮助类
     * @param name       员工名称
     * @return 返回员工信息
     */
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('sys.manager.list.post')")
    public BaseResponse queryManagers(PageHelper<Manager> pageHelper, String name, Authentication authentication) {
        return BaseResponse.build(adminUserService.queryManagers(pageHelper, name, getUserId(authentication)));
    }

    /**
     * 查询员工创建的角色
     *
     * @return 返回员工创建的角色
     */
    @GetMapping("/roles")
    @PreAuthorize("hasAuthority('sys.manager.roles.get')")
    public List<AdminRole> queryRoles(Authentication authentication) {
        return adminRoleService.queryManagerCreatedRoles(getUserId(authentication));
    }

    /**
     * 新增员工
     *
     * @param manager 员工信息
     * @return 成功返回1 失败返回0 -1 名称已经存在
     */
    @PostMapping
    @PreAuthorize("hasAuthority('sys.manager.post')")
    public int addManager(@RequestBody Manager manager, Authentication authentication) {
        return adminUserService.addManager(manager.addCreator(getUserId(authentication)));
    }

    /**
     * 删除员工
     *
     * @param ids 员工id
     * @return 成功返回》0 失败返回0
     */
    @DeleteMapping
    @PreAuthorize("hasAuthority('sys.manager.delete')")
    public int deleteManagers(long ids[], Authentication authentication) {
        return adminUserService.deleteManagers(ids, getUserId(authentication));
    }

    /**
     * 根据员工id查询员工信息
     *
     * @param id 员工id
     * @return 返回员工信息
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('sys.manager.get')")
    public Manager queryById(@PathVariable long id, Authentication authentication) {
        return adminUserService.queryManagerById(id, getUserId(authentication));
    }

    /**
     * 更新员工信息
     *
     * @param manager 员工信息
     * @return 成功返回1 失败返回0 员工名称存在返回-1
     */
    @PutMapping
    @PreAuthorize("hasAuthority('sys.manager.put')")
    public int updateManager(@RequestBody Manager manager, Authentication authentication) {
        manager.setCreator(getUserId(authentication));
        return adminUserService.updateManager(manager);
    }

    /**
     * 获得管理员id
     *
     * @param authentication 认证信息
     * @return 返回管理员id
     */
    private long getUserId(Authentication authentication) {
        Manager manager = adminUserService.queryByName(authentication.getName());
        return Objects.nonNull(manager) ? manager.getId() : 0;
    }
}

