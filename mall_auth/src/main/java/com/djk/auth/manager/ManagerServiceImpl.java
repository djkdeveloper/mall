package com.djk.auth.manager;

import com.djk.utils.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by dujinkai on 2018/8/12.
 * 管理员服务接口实现
 */
@Service
@Slf4j
public class ManagerServiceImpl implements ManagerService {

    /**
     * 注入管理员数据库接口
     */
    @Autowired
    private ManagerMapper managerMapper;

    /**
     * 注入权限数据库接口
     */
    @Autowired
    private AuthorityMapper authorityMapper;

    @Log
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        // 获得管理员信息
        Manager manager = this.queryByName(s);

        if (Objects.isNull(manager)) {
            return null;
        }
        return manager.convertToUserDetails();
    }

    @Log
    @Override
    public Manager queryByName(String name) {
        Manager manager = managerMapper.queryByName(name);

        if (Objects.isNull(manager)) {
            log.error("queryByName fail ....name :{}", name);
            return manager;
        }

        // 设置管理员的权限
        manager.setAuthorities(authorityMapper.queryManagerAuthoritys(manager.getId()));
        return manager;
    }
}
