package com.ny.nycommoncore.context;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * 负载均衡策略Holder(?)
 *
 * @author N.Y
 * @date 2020-03-27 15:58
 */
public class LbIsolationContextHolder {
    private static final ThreadLocal<String> VERSION_CONTEXT = new TransmittableThreadLocal<>();

    public static void setVersion(String version){
        VERSION_CONTEXT.set(version);
    }

    public static String getVersion(String version){
        return VERSION_CONTEXT.get();
    }

    public static void clear(){
        VERSION_CONTEXT.remove();
    }
}
