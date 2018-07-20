package com.djk.role;

import lombok.Data;

/**
 * Created by dujinkai on 2018/7/16.
 * 角色和权限的关联实体
 */
@Data
public class RoleAuthority {

    /**
     * 主键id
     */
    private long id;

    /**
     * 角色id
     */
    private long roleId;

    /**
     * 权限id
     */
    private long authorityId;
}
