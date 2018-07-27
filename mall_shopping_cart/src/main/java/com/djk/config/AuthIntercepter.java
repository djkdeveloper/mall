package com.djk.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dujinkai on 2018/7/9.
 * 认证拦截器
 */
@Slf4j
public class AuthIntercepter extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 这边不做认证 因为zuul网关已经做过认证 这边只获取用户id
        request.setAttribute("userId", request.getHeader("userId"));
        return super.preHandle(request, response, handler);
    }
}
