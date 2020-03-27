package com.ny.nycommoncore.exception;

/**
 * 分布式锁异常
 *
 * @author N.Y
 * @date 2020-03-27 16:13
 */
public class LockException extends RuntimeException{
    private static final long serialVersionUID = 1849820637035399594L;

    public LockException(String message){
        super(message);
    }

}
