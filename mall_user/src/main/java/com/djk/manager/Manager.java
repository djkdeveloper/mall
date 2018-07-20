package com.djk.manager;

import com.djk.authority.Authority;
import com.djk.role.AdminRole;
import com.djk.utils.CustomLocalDateTimeDeserializer;
import com.djk.utils.CustomLocalDateTimeSerializer;
import com.djk.utils.MD5Utils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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
     * 管理员对应的角色
     */
    private AdminRole role;

    /**
     * 管理员对应的权限信息
     */
    private List<Authority> authorities;

    /**
     * 员工与角色的关系实体
     */
    private ManagerRole managerRole;

    /**
     * 设置员工id
     */
    public void setMangerId() {
        if (Objects.isNull(managerRole)) {
            return;
        }

        managerRole.setManagerId(this.id);
    }


    /**
     * 校验密码是否正确
     *
     * @param password 密码明文
     * @return 正确返回true  错误返回flase
     */
    public boolean validatePassword(String password) {
        if (StringUtils.isEmpty(password)) {
            return false;
        }

        return MD5Utils.getInstance().createMd5(password).equalsIgnoreCase(this.password);
    }

    // 清理密码
    public Manager cleanPassword() {
        this.password = "*****";
        return this;
    }


    /**
     * 添加创建人
     *
     * @param creator 创建人
     * @return 返回当前对象
     */
    public Manager addCreator(long creator) {
        this.creator = creator;
        return this;
    }

    /**
     * 给密码进行md5加密
     */
    public void md5Password() {
        this.password = MD5Utils.getInstance().createMd5(this.password);
    }

}
