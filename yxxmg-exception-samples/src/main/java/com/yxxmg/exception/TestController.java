package com.yxxmg.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : zhaoyan
 * @version : 1.0
 * @description :
 * @since : 2023/2/26
 */
@RestController
@Slf4j
public class TestController {
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/test/{id}")
    public String test(@PathVariable("id") String id) {
        try {
            int i = Integer.parseInt(id) / 0;
        } catch (Exception e) {
            // 这边可以配置化 制作测试用
            throw new BusinessException("10001", "1234567");
        }
        return id;
    }

    @PostMapping("/test2")
    public String test2(@RequestBody @Validated CheckDTO checkDTO) {
        return checkDTO.getName();
    }

    @ExceptionHandler(BusinessException.class)
    public String exception(BusinessException ex) {
        String message = messageSource.getMessage(ex.getCode(), ex.getArgs(), LocaleContextHolder.getLocale());
        System.out.println(message);
        return message;
    }

    @ExceptionHandler(Exception.class)
    public String exception(Exception ex) {
        log.error("请求异常了", ex);
        return "异常了" + ex.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String exception(MethodArgumentNotValidException ex) {
        log.error("请求异常了", ex);
        List<String> list = ex.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
        return "异常了" + StringUtils.join(list, ";");
    }
}
