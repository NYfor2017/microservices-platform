package com.ny.nylog.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author N.Y
 * @date 2020-03-26 22:00
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "ny.audit-log")
@RefreshScope
public class AuditLogProperties {

    /**
     * 是否开启审计日志
     */
    private Boolean enabled = false;

    /**
     * 日志记录类型(logger/redis/db/es)
     */
    private String logType;
}
