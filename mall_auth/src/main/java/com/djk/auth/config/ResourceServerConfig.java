package com.djk.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by dujinkai on 2018/8/6.
 * 资源服务配置类
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    /**
     * 不需要认证的请求
     */
    private static String[] WHITE_LIST = new String[]{"/adduser"};

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()   // 前后端分离 不需要csrf
                .exceptionHandling().authenticationEntryPoint(new NoLoginAuthenticationEntryPoint()).and() // 设置没有登录认证时候的错误提醒为401
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers(WHITE_LIST).permitAll().anyRequest().authenticated().and();//配置order访问控制，必须认证过后才可以访问
    }
}
