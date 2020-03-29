package com.ny.nyredis.constant;

/**
 * redis 工具常量
 *
 * @author N.Y
 * @date 2020-03-27 17:23
 */
public class RedisToolsConstant {
    private RedisToolsConstant(){
        throw new IllegalStateException("Utility class");
    }

    /**
     * single Redis
     */
    public final static int SINGLE = 1;

    /**
     * Redis cluster
     */
    public final static int CLUSTER = 2;
}
