package com.ny.nylog.config;

import com.ny.nylog.properties.AuditLogProperties;
import com.ny.nylog.properties.LogDbProperties;
import com.ny.nylog.properties.TraceProperties;
import com.zaxxer.hikari.HikariConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author N.Y
 * @date 2020-03-27 11:38
 */
@EnableConfigurationProperties({TraceProperties.class, AuditLogProperties.class})
public class LogAutoConfigure {
    /**
     * 日志数据库配置
     */
    @Configuration
    @ConditionalOnClass(HikariConfig.class)
    @EnableConfigurationProperties(LogDbProperties.class)
    public static class LogDbAutoConfigure{}
}
