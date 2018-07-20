package com.djk.manager;

import lombok.Data;

/**
 * Created by dujinkai on 2018/7/16.
 * 员工和角色的关系实体
 */
@Data
public class ManagerRole {

    /**
     * 主键id
     */
    private long id;

    /**
     * 员工id
     */
    private long managerId;

    /**
     * 角色id
     */
    private long roleId;
}
