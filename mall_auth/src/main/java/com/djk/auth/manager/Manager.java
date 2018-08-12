package com.djk.auth.manager;

import com.djk.utils.CustomLocalDateTimeDeserializer;
import com.djk.utils.CustomLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dujinkai on 2018/6/30.
 * 管理员实体类
 */
@Data
public class Manager {

    /**
     * 主键id
     */
    private long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 删除标记 0 未删除 1 已删除
     */
    private String delFlag;

    /**
     * 创建人
     */
    private long creator;

    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;


    /**
     * 管理员的权限
     */
    private List<Authority> authorities;


    /**
     * 转化成用户对象
     *
     * @return 返回用户对象
     */
    public UserDetails convertToUserDetails() {

        // 用户的权限信息
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        // 过滤出有权限的权限信息
        authorities = authorities.stream().filter(authority1 -> !StringUtils.isEmpty(authority1.getAuthority())).collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(authorities)) {
            grantedAuthorities = authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getAuthority())).collect(Collectors.toList());
        }

        return new User(this.getUserName(), this.getPassword(), grantedAuthorities);
    }

}
