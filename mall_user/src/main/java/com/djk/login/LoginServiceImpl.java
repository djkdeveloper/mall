package com.djk.login;

import com.djk.manager.Manager;
import com.djk.manager.ManagerService;
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
     * jwt秘钥
     */
    @Value("${jwt.jwtSecretKey}")
    private String jwtSecretKey;

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
}
