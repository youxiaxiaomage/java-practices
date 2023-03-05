package com.yxxmg.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zhaoyan
 * @version : 1.0
 * @description :
 * @since : 2023/2/26
 */
@RestController
public class TestController {
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/test/{id}")
    public String test(@PathVariable("id") String id) {
        try {
            int i = Integer.parseInt(id) / 0;
        } catch (Exception e) {
            throw new BusinessException("10001", "1234567");
        }
        return id;
    }

    @ExceptionHandler(BusinessException.class)
    public String exception(BusinessException ex) {
        String message = messageSource.getMessage(ex.getCode(), ex.getArgs(), LocaleContextHolder.getLocale());
        System.out.println(message);
        return message;
    }

}
