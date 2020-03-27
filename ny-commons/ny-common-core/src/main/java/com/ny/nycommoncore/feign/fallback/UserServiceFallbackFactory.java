package com.ny.nycommoncore.feign.fallback;

import com.ny.nycommoncore.feign.UserService;
import com.ny.nycommoncore.model.LoginAppUser;
import com.ny.nycommoncore.model.SysUser;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * userService降级工厂
 *
 * @author N.Y
 * @date 2020-03-27 15:02
 */
@Slf4j
@Component
public class UserServiceFallbackFactory implements FallbackFactory<UserService> {
    @Override
    public UserService create(Throwable throwable) {
        return new UserService() {
            public SysUser selectByUsername(String username){
                log.error("通过用户名查询用户异常：{}", username, throwable);
                return new SysUser();
            }

            public LoginAppUser findByUsername(String username){
                log.error("通过用户名查询用户异常：{}", username, throwable);
                return new LoginAppUser();
            }

            public LoginAppUser findByMobile(String mobile){
                log.error("通过手机号查询用户异常：{}", mobile, throwable);
                return new LoginAppUser();
            }

            public LoginAppUser findByOpenId(String openId){
                log.error("通过openId查询用户异常：{}", openId, throwable);
                return new LoginAppUser();
            }
        };
    }
}
