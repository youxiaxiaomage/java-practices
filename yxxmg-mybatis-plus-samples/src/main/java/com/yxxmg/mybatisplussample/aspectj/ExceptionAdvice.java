package com.yxxmg.mybatisplussample.aspectj;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.MessageFormat;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2022/11/3
 */
@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {
    @ExceptionHandler
    public String exception(Exception ex) {
        log.error("请求异常了", ex);
        return MessageFormat.format("请求异常了：{0}", ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String ex(MethodArgumentNotValidException ex) {
        log.error("参数校验异常了", ex);
        return MessageFormat.format("参数校验异常了：{0}",
                ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).findFirst().orElse(""));
    }
}
