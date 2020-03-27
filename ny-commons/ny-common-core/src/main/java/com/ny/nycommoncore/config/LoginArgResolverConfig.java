package com.ny.nycommoncore.config;

import com.ny.nycommoncore.feign.UserService;
import com.ny.nycommoncore.resolver.ClientArgumentResolver;
import com.ny.nycommoncore.resolver.TokenArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 公共配置类，一些公共工具配置
 *
 * @author N.Y
 * @date 2020-03-27 15:54
 */
public class LoginArgResolverConfig implements WebMvcConfigurer {
    @Lazy
    @Autowired
    private UserService userService;

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
        //注入用户信息
        argumentResolvers.add(new TokenArgumentResolver(userService));
        //注入应用信息
        argumentResolvers.add(new ClientArgumentResolver());
    }
}
