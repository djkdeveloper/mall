package com.djk.login;

import com.djk.customer.Customer;
import com.djk.customer.CustomerService;
import com.djk.manager.Manager;
import com.djk.manager.ManagerService;
import com.djk.utils.Log;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.compression.CompressionCodecs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

/**
 * Created by dujinkai on 2018/6/30.
 * 登录服务实现
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    /**
     * 注入管理用户服务接口
     */
    @Autowired
    private ManagerService adminUserService;

    /**
     * 注入会员服务接口
     */
    @Autowired
    private CustomerService customerService;

    /**
     * 后端jwt秘钥
     */
    @Value("${jwt.jwtSecretKey}")
    private String jwtSecretKey;

    /**
     * 前端jwt秘钥
     */
    @Value("${jwt.siteJwtSecretKey}")
    private String siteJwtSecretKey;

    @Log
    @Override
    public AdminLoginResult login(Manager adminUser) {

        if (Objects.isNull(adminUser)) {
            log.error("login fail due to adminUser is null...");
            return AdminLoginResult.buildLoginFail();
        }

        // 从数据库中查出用户信息
        Manager adminUserInDb = adminUserService.queryByName(adminUser.getUserName());

        if (Objects.isNull(adminUserInDb)) {
            log.error("login fail due to  user not exist...");
            return AdminLoginResult.buildLoginFail();
        }

        /**
         * 密码校验不成功直接返回
         */
        if (!adminUserInDb.validatePassword(adminUser.getPassword())) {
            log.error("login fail due to  password error...");
            return AdminLoginResult.buildLoginFail();
        }

        return AdminLoginResult.buildLoginSuccess(Jwts.builder().setSubject(adminUserInDb.getUserName())
                .compressWith(CompressionCodecs.DEFLATE)
                .signWith(SignatureAlgorithm.HS256, jwtSecretKey)
                .setIssuedAt(new Date())
                .claim("id", adminUserInDb.getId())
                .setExpiration(Date.from(Instant.now().plus(7, ChronoUnit.DAYS))) // 有效期一个星期
                .compact());
    }

    @Log
    @Override
    public SiteLoginResult login(Customer customer) {

        if (Objects.isNull(customer)) {
            log.error("login fail due to customer is null...");
            return SiteLoginResult.buildLoginFail();
        }

        // 从数据库中查出会员信息
        Customer customerInDb = customerService.queryCustomerByName(customer.getUsername());

        // 没有用户信息直接返回
        if (Objects.isNull(customerInDb)) {
            log.error("login fail due customer is not exist....");
            return SiteLoginResult.buildLoginFail();
        }

        // 密码不对返回失败
        if (!customerInDb.validatePassword(customer.getPassword())) {
            log.error("login fail due to password is error....");
            return SiteLoginResult.buildLoginFail();
        }

        return SiteLoginResult.buildLoginSuccess(Jwts.builder().setSubject(customer.getUsername())
                .compressWith(CompressionCodecs.DEFLATE)
                .signWith(SignatureAlgorithm.HS256, siteJwtSecretKey)
                .setIssuedAt(new Date())
                .claim("id", customerInDb.getId())
                .setExpiration(Date.from(Instant.now().plus(7, ChronoUnit.DAYS))) // 有效期一个星期
                .compact());
    }
}
