package com.ny.nylog.aspect;

import com.ny.nylog.annotation.AuditLog;
import com.ny.nylog.model.Audit;
import com.ny.nylog.properties.AuditLogProperties;
import com.ny.nylog.service.IAuditService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * 审计日志配置
 *
 * @author N.Y
 * @date 2020-03-26 21:50
 */
@Slf4j
@Aspect
@ConditionalOnClass({HttpServletRequest.class, RequestContextHolder.class})
public class AuditLogAspect {

    @Value("TX-Manager")
    private String applicationName;

    private AuditLogProperties auditLogProperties;
    private IAuditService auditService;

    public AuditLogAspect(AuditLogProperties auditLogProperties, IAuditService auditService) {
        this.auditLogProperties = auditLogProperties;
        this.auditService = auditService;
    }

    /**
     * 用于SqEL表达式解析
     */
    private SpelExpressionParser spelExpressionParser = new SpelExpressionParser();

    /**
     * 用于获取方法参数定义名字
     */
    private DefaultParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();

    public void beforeMethod(JoinPoint joinPoint, AuditLog auditLog) {
        //判断功能是否开启
        if (auditLogProperties.getEnabled()) {
            if (auditService == null) {
                log.warn("AuditLogAspect - auditService is null");
                return;
            }
            if (auditLog == null) {
                //获取类上的注解
                auditLog = joinPoint.getTarget().getClass().getDeclaredAnnotation(AuditLog.class);
            }
            Audit audit = getAudit(auditLog, joinPoint);
            auditService.save(audit);
        }
    }

    private String getValBySqEL(String spEL, MethodSignature methodSignature, Object[] args) {
        //获取方法形参名数组
        String[] paramNames = nameDiscoverer.getParameterNames(methodSignature.getMethod());
        if (paramNames != null && paramNames.length > 0) {
            Expression expression = spelExpressionParser.parseExpression(spEL);
            //spring的表达式上下文对象
            EvaluationContext context = new StandardEvaluationContext();
            //给上下文赋值
            for (int i = 0; i < args.length; i++) {
                context.setVariable(paramNames[i], args[i]);
            }
            return expression.getValue(context).toString();
        }
        return null;
    }

    private Audit getAudit(AuditLog auditLog, JoinPoint joinPoint){
        Audit audit = new Audit();
        audit.setTimestamp(LocalDateTime.now());
        audit.setApplicationName(applicationName);

        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        audit.setClassName(methodSignature.getDeclaringTypeName());
        audit.setMethodName(methodSignature.getName());

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String userId = request.getHeader("x-userid-header");
        String userName = request.getHeader("x-user-header");
        String clientId = request.getHeader("x-tenant-header");
        audit.setUserId(userId);
        audit.setUserName(userName);
        audit.setClientId(clientId);

        String operation = auditLog.operation();
        if(operation.contains("#")){
            //获取方法参数值
            Object[] args = joinPoint.getArgs();
            operation = getValBySqEL(operation, methodSignature, args);
        }
        audit.setOperation(operation);

        return audit;
    }
}
