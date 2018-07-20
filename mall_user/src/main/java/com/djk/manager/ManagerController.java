package com.djk.manager;

import com.djk.role.AdminRole;
import com.djk.role.AdminRoleService;
import com.djk.utils.BaseResponse;
import com.djk.utils.LoginUtils;
import com.djk.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
     * 注入请求（此处是代理 所以不会有线程安全问题）
     */
    @Autowired
    private HttpServletRequest request;

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
    public Manager queryCurrentUser() {
        return adminUserService.queryById(LoginUtils.getInstance().getUserId(request));
    }

    /**
     * 查询用户的权限信息
     *
     * @return 返回用户信息和权限信息
     */
    @GetMapping("/managerauthoritys")
    public Manager queryManagerAuthoritys() {
        return adminUserService.queryUserAuthority(LoginUtils.getInstance().getUserId(request));
    }

    /**
     * 分页查询员工信息
     *
     * @param pageHelper 分页帮助类
     * @param name       员工名称
     * @return 返回员工信息
     */
    @PostMapping("/list")
    public BaseResponse queryManagers(PageHelper<Manager> pageHelper, String name) {
        return BaseResponse.build(adminUserService.queryManagers(pageHelper, name, LoginUtils.getInstance().getUserId(request)));
    }

    /**
     * 查询员工创建的角色
     *
     * @return 返回员工创建的角色
     */
    @GetMapping("/roles")
    public List<AdminRole> queryRoles() {
        return adminRoleService.queryManagerCreatedRoles(LoginUtils.getInstance().getUserId(request));
    }

    /**
     * 新增员工
     *
     * @param manager 员工信息
     * @return 成功返回1 失败返回0 -1 名称已经存在
     */
    @PostMapping
    public int addManager(@RequestBody Manager manager) {
        return adminUserService.addManager(manager.addCreator(LoginUtils.getInstance().getUserId(request)));
    }

    /**
     * 删除员工
     *
     * @param ids 员工id
     * @return 成功返回》0 失败返回0
     */
    @DeleteMapping
    public int deleteManagers(long ids[]) {
        return adminUserService.deleteManagers(ids, LoginUtils.getInstance().getUserId(request));
    }

    /**
     * 根据员工id查询员工信息
     *
     * @param id 员工id
     * @return 返回员工信息
     */
    @GetMapping("/{id}")
    public Manager queryById(@PathVariable long id) {
        return adminUserService.queryManagerById(id, LoginUtils.getInstance().getUserId(request));
    }

    /**
     * 更新员工信息
     *
     * @param manager 员工信息
     * @return 成功返回1 失败返回0 员工名称存在返回-1
     */
    @PutMapping
    public int updateManager(@RequestBody Manager manager) {
        manager.setCreator(LoginUtils.getInstance().getUserId(request));
        return adminUserService.updateManager(manager);
    }
}

