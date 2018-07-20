package com.djk.login;

import com.djk.manager.Manager;
import com.djk.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dujinkai on 2018/6/30.
 * 登录控制器
 */
@RestController
public class LoginController {

    /**
     * 注入登录服务接口
     */
    @Autowired
    private LoginService loginService;

    /**
     * 登录
     *
     * @param manager 用户信息
     * @return 成功返回0 失败返回-1
     */
    @PostMapping("/login")
    @Log
    public AdminLoginResult login(@RequestBody Manager manager) {
        return loginService.login(manager);
    }
}
