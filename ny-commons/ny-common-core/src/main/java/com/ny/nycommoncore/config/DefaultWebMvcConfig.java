package com.ny.nycommoncore.config;

import com.ny.nycommoncore.feign.UserService;
import com.ny.nycommoncore.resolver.ClientArgumentResolver;
import com.ny.nycommoncore.resolver.TokenArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * 默认SpringMVC拦截器
 *
 * @author N.Y
 * @date 2020-03-27 14:56
 */
public class DefaultWebMvcConfig extends WebMvcConfigurationSupport {
    @Lazy
    @Autowired
    private UserService userService;

    /**
     * Token参数解析
     * @param argumentResolvers
     */
    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        //注入用户信息
        argumentResolvers.add(new TokenArgumentResolver(userService));
        //注入应用信息
        argumentResolvers.add(new ClientArgumentResolver());
    }

    /**
     * 设置资源文件目录
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/");
        super.addResourceHandlers(registry);
    }
}
