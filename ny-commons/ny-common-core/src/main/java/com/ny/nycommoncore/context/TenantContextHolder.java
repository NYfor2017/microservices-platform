package com.ny.nycommoncore.context;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * 租户holder
 *
 * @author N.Y
 * @date 2020-03-27 16:08
 */
public class TenantContextHolder {

    private static final ThreadLocal<String> CONTEXT = new TransmittableThreadLocal<>();

    public static void setTenant(String tenant){
        CONTEXT.set(tenant);
    }

    public String getTenant(){
        return CONTEXT.get();
    }

    public static void clear(){
        CONTEXT.remove();
    }
}
