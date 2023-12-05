package com.yxxmg.hbase.exception;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/20
 */
public class BindingException extends RuntimeException {
    public BindingException() {
        super();
    }

    public BindingException(String message) {
        super(message);
    }

    public BindingException(String message, Throwable cause) {
        super(message, cause);
    }

    public BindingException(Throwable cause) {
        super(cause);
    }
}
