package com.djk.authority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * Created by dujinkai on 2018/7/11.
 * 权限Ribbon 服务调用
 */
@Service
public class AuthorityRibbon {

    /**
     * 注入
     */
    @Autowired
    private RestTemplate restTemplate;


    /**
     * 查询当前用户的所有权限
     *
     * @return 返回当前用户的所有权限
     */
    public Authority[] queryManagerAuthority() {


        return this.restTemplate.getForObject("http://mall-user/authority", Authority[].class);
    }
}
