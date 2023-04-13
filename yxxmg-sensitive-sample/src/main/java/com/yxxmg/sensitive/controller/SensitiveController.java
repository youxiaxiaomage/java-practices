package com.yxxmg.sensitive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yxxmg.sensitive.domain.Student;

/**
 * @author : zhaoyan
 * @version : 1.0
 * @description :
 * @since : 2023/4/12
 */
@RestController
@RequestMapping("/sensitive")
public class SensitiveController {
    @GetMapping("/test")
    public Student getUser() {
        return new Student().setPhoneNumber("15371002835").setEmail("1187961583@qq.com").setUserId("1");
    }
}
