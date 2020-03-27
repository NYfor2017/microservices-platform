package com.ny.nylog.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author N.Y
 * @date 2020-03-26 21:46
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditLog {

    /**
     * 操作信息
     * @return
     */
    String operation();
}
