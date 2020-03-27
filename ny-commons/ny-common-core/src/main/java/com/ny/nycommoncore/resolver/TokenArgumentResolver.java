package com.ny.nycommoncore.resolver;

import cn.hutool.core.util.StrUtil;
import com.ny.nycommoncore.annotation.LoginUser;
import com.ny.nycommoncore.constant.SecurityConstants;
import com.ny.nycommoncore.feign.UserService;
import com.ny.nycommoncore.model.SysRole;
import com.ny.nycommoncore.model.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author N.Y
 * @date 2020-03-27 15:19
 */
@Slf4j
public class TokenArgumentResolver implements HandlerMethodArgumentResolver {
    private UserService userService;

    public TokenArgumentResolver(UserService userService){
        this.userService = userService;
    }

    /**
     * 入参筛选
     *
     * @param methodParameter 参数集合
     * @return 格式化后的参数
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        LoginUser loginUser = methodParameter.getParameterAnnotation(LoginUser.class);
        boolean isFull = loginUser.isFull();
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        String userId = request.getHeader(SecurityConstants.USER_ID_HEADER);
        String username = request.getHeader(SecurityConstants.USER_HEADER);
        String roles = request.getHeader(SecurityConstants.ROLE_HEADER);
        if(StrUtil.isBlank(username)){
            log.warn("resolveArgument error username is empty");
            return null;
        }
        SysUser user;
        if(isFull){
            user = userService.selectByUsername(username);
        }else {
            user = new SysUser();
            user.setId(Long.valueOf(userId));
            user.setUsername(username);
        }
        List<SysRole> sysRoleList = new ArrayList<>();
        Arrays.stream(roles.split(",")).forEach(role->{
            SysRole sysRole = new SysRole();
            sysRole.setCode(role);
            sysRoleList.add(sysRole);
        });
        user.setRoles(sysRoleList);
        return user;
    }
}
