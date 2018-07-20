package com.djk.role;

import com.djk.authority.Authority;
import com.djk.utils.CustomLocalDateTimeDeserializer;
import com.djk.utils.CustomLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by dujinkai on 2018/7/9.
 * 管理员的角色
 */
@Data
public class AdminRole {

    /**
     * 主键id
     */
    private int id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色的创建人
     */
    private long creator;

    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime modifyTime;

    /**
     * 角色和权限的关系集合
     */
    private List<RoleAuthority> roleAuthorities;

    /**
     * 角色下的权限
     */
    private List<Authority> authorities;

    /**
     * 设置角色id
     */
    public void setRoleId() {
        if (CollectionUtils.isEmpty(this.roleAuthorities)) {
            return;
        }
        roleAuthorities.stream().forEach(roleAuthority -> roleAuthority.setRoleId(this.id));
    }

    /**
     * 添加创建人
     *
     * @param creator 创建人
     * @return 返回当前对象
     */
    public AdminRole addCreator(long creator) {
        this.creator = creator;
        return this;
    }
}
