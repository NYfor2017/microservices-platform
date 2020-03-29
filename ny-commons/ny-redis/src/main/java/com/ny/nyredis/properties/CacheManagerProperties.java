package com.ny.nyredis.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author N.Y
 * @date 2020-03-27 18:22
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "ny.cache-manager")
public class CacheManagerProperties {
    private List<CacheConfig> configs;

    @Setter
    @Getter
    public static class CacheConfig {
        /**
         * cache key
         */
        private String key;

        /**
         * 过期时间，sec
         */
        private long second = 60;
    }
}
