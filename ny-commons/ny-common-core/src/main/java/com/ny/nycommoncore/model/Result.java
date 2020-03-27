package com.ny.nycommoncore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.lang.annotation.Documented;

/**
 * @author N.Y
 * @date 2020-03-27 16:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    private T datas;
    private Integer resp_code;
    private String resp_msg;

    public static <T> Result<T> succeed(String msg) {
        return succeedWith(null, CodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> Result<T> succeed(T model, String msg) {
        return succeedWith(model, CodeEnum.SUCCESS.getCode(), msg);
    }

    public static<T> Result<T> succeed(T model){
        return succeedWith(model, CodeEnum.SUCCESS.getCode(), "");
    }

    private static <T> Result<T> succeedWith(T datas, Integer code, String msg) {
        return new Result<>(datas, code, msg);
    }

    public static <T> Result<T> failed(String msg){
        return failedWith(null, CodeEnum.ERROR.getCode(), msg);
    }

    public static <T> Result<T> failed(T model, String msg){
        return failedWith(model, CodeEnum.ERROR.getCode(), msg);
    }

    public static <T> Result<T> failedWith(T datas, Integer code, String msg){
        return new Result<>(datas, code, msg);
    }


}
