package com.ny.nycommoncore.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author N.Y
 * @date 2020-03-27 14:47
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
public class SysUser extends SuperEntity {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String nickname;
    private String headImgUrl;
    private String mobile;
    private Integer sex;
    private Boolean enabled;
    private String type;
    private String openId;
    @TableLogic
    private boolean isDel;

    @TableField(exist = false)
    private List<SysRole> roles;
    @TableField(exist = false)
    private String roleId;
    @TableField(exist = false)
    private String oldPassword;
    @TableField(exist = false)
    private String newPassword;
}

