package com.yxxmg.okttp;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/8/5
 */
public class OkhttpException extends RuntimeException {
    private static final long serialVersionUID = 6474036445952514914L;

    public OkhttpException(String message) {
        super(message);
    }
}
