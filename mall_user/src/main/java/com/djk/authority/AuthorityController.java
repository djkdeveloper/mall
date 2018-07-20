package com.djk.authority;

import com.djk.utils.LoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dujinkai on 2018/7/11.
 * 权限控制器
 */
@RestController
@RequestMapping("/authority")
public class AuthorityController {

    /**
     * 注入权限服务接口
     */
    @Autowired
    private AuthorityService authorityService;

    /**
     * 注入
     */
    @Autowired
    private HttpServletRequest request;

    /**
     * 查询当前用户的所有权限
     *
     * @return 返回当前用户的所有权限
     */
    @GetMapping
    public Authority[] queryAuthorities() {
        return authorityService.queryManagerAuthoritys(LoginUtils.getInstance().getUserId(request)).stream().toArray(Authority[]::new);
    }
}
