package com.ny.nycommoncore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 密码工具类
 *
 * @author N.Y
 * @date 2020-03-27 14:42
 */
public class DefaultPasswordConfig {

    /**
     * 装配BCryptPasswordEncoder用户密码的匹配
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
