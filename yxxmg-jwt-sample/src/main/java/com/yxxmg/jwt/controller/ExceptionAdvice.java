package com.yxxmg.jwt.controller;

import com.yxxmg.jwt.vo.ResponseVO;
import com.yxxmg.jwt.vo.TokenVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : TODO
 * @since : 2023/10/16
 */
@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseVO<TokenVO> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        return ResponseVO.fail(e.getAllErrors().stream().findFirst().map(ObjectError::getDefaultMessage).orElse(""));
    }
    @ExceptionHandler(Exception.class)
    public ResponseVO<TokenVO> defaultException(Exception e) {
        log.error("请求发生了异常", e);
        return ResponseVO.fail(e.getMessage());
    }
}
