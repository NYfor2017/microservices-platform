package com.ny.nycommoncore.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author N.Y
 * @date 2020-03-27 14:49
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_role")
public class SysRole extends SuperEntity {
    private static final long serialVersionUID = 4497149010220586111L;
    private String code;
    private String name;
    private Long userId;
}
