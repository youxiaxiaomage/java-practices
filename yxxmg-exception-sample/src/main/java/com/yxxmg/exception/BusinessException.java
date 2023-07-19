package com.yxxmg.exception;

import lombok.Getter;

/**
 * @author : zhaoyan
 * @version : 1.0
 * @description :
 * @since : 2023/2/26
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 4379663135843148418L;
    @Getter
    private final String code;
    @Getter
    private Object[] args;

    public BusinessException(String code, Object... args) {
        this.code = code;
        this.args = args;
    }

    public BusinessException(String code) {
        this.code = code;
    }

}
