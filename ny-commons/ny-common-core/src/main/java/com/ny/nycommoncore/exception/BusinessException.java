package com.ny.nycommoncore.exception;

/**
 * @author N.Y
 * @date 2020-03-27 16:12
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 2842337968154308376L;

    public BusinessException(String message){
        super(message);
    }
}
