package com.ny.nycommoncore.resolver;

import cn.hutool.core.util.StrUtil;
import com.ny.nycommoncore.annotation.LoginClient;
import com.ny.nycommoncore.constant.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @author N.Y
 * @date 2020-03-27 15:45
 */
@Slf4j
public class ClientArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * 入参筛选
     *
     * @param methodParameter 参数集合
     * @return 格式化后的参数
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(LoginClient.class) && methodParameter.getParameterType().equals(String.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        String clientId = request.getHeader(SecurityConstants.TENENT_HEADER);
        if(StrUtil.isBlank(clientId)){
            log.warn("resolveArgument error clientId is empty");
        }
        return clientId;
    }
}
