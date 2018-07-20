package com.djk.role;

import com.djk.authority.Authority;
import com.djk.authority.AuthorityService;
import com.djk.utils.BaseResponse;
import com.djk.utils.LoginUtils;
import com.djk.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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


    @Autowired
    private HttpServletRequest request;


    /**
     * 分页查询角色信息
     *
     * @param pageHelper 分页帮助类
     * @param name       角色名称
     * @return 返回查询信息
     */
    @PostMapping("/list")
    public BaseResponse queryRoles(PageHelper<AdminRole> pageHelper, String name) {
        return BaseResponse.build(adminRoleService.queryRoles(pageHelper, name, LoginUtils.getInstance().getUserId(request)));
    }


    /**
     * 查询当前角色的权限
     *
     * @return 返回当前角色的权限
     */
    @GetMapping("/authoritiesinduction")
    public Authority queryAuthoritiesInduction() {
        return authorityService.queryManagerAuthoritysInduction(LoginUtils.getInstance().getUserId(request));
    }

    /**
     * 新增角色
     *
     * @param adminRole 角色信息
     * @return 成功》0 失败返回0
     */
    @PostMapping
    public int addRole(@RequestBody AdminRole adminRole) {
        return adminRoleService.addRoles(adminRole.addCreator(LoginUtils.getInstance().getUserId(request)));
    }

    /**
     * 删除角色
     *
     * @param ids 角色id
     * @return 成功返回》0 失败返回0 -1 角色被员工使用不能删除
     */
    @DeleteMapping
    public int deleteRoles(long[] ids) {
        return adminRoleService.deleteRoles(ids, LoginUtils.getInstance().getUserId(request));
    }

    /**
     * 根据id查询角色信息
     *
     * @param id 角色id
     * @return 返回角色信息
     */
    @GetMapping("/{id}")
    public AdminRole queryById(@PathVariable long id) {
        return adminRoleService.queryRoleById(id, LoginUtils.getInstance().getUserId(request));
    }

    /**
     * 更新角色信息
     *
     * @param adminRole 角色信息
     * @return 成功返回1 失败返回0
     */
    @PutMapping
    public int updateRole(@RequestBody AdminRole adminRole) {
        adminRole.setCreator(LoginUtils.getInstance().getUserId(request));
        return adminRoleService.updateRole(adminRole);
    }
}
