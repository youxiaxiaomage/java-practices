package com.yxxmg.jwt.vo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/16
 */
@Data
@Accessors(chain = true)
public class ResponseVO<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;
    public static <T> ResponseVO<T> success(String message, T data) {
        return new ResponseVO<T>().setCode(0).setMessage(message).setData(data);
    }
    public static <T> ResponseVO<T> success(String message) {
        return success(message, null);
    }
    public static  <T> ResponseVO<T> fail(String message) {
        return fail(message, null);
    }
    public static <T> ResponseVO<T> fail(String message, T data) {
        return new ResponseVO<T>().setCode(500).setMessage(message).setData(data);
    }
    public static <T> ResponseVO<T> fail(int code, String message, T data) {
        return new ResponseVO<T>().setCode(code).setMessage(message).setData(data);
    }

    public static <T> ResponseVO<T> fail(int code, String message) {
        return fail(code, message, null);
    }
}
