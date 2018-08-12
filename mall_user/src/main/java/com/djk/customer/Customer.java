package com.djk.customer;

import com.djk.utils.CustomLocalDateTimeDeserializer;
import com.djk.utils.CustomLocalDateTimeSerializer;
import com.djk.utils.MD5Utils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * Created by dujinkai on 2018/7/13.
 * 会员实体
 */
@Data
public class Customer {

    /**
     * 主键id
     */
    private long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 用户头像
     */
    private String pic;

    /**
     * 删除标记 0 未删除 1 已删除
     */
    private String delFlag;


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
     * 清理密码
     *
     * @return 返回当前对象
     */
    public Customer cleanPassword() {
        this.password = "******";
        return this;
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
}
