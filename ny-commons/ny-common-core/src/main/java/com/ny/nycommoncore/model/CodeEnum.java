package com.ny.nycommoncore.model;

/**
 * @author N.Y
 * @date 2020-03-27 16:27
 */
public enum CodeEnum {
    SUCCESS(0),
    ERROR(1);

    private Integer code;
    CodeEnum(Integer code){
        this.code = code;
    }
    public Integer getCode(){
        return code;
    }
}
