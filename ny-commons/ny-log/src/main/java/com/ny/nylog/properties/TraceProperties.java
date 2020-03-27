package com.ny.nylog.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author N.Y
 * @date 2020-03-26 22:07
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "zlt.trace")
public class TraceProperties {
    /**
     * 是否开启日志链路追踪
     */
    private Boolean enable = false;
}
