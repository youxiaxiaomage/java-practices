package com.yxxmg.mybatisplussample.service;

import com.yxxmg.mybatisplussample.api.CustomService;
import com.yxxmg.mybatisplussample.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 构造器注入
 * @since : 2022/11/7
 */
@Service
@RequiredArgsConstructor
public class ConstructorService {
    private final UserService userService;

    @Autowired
    private CustomService customService;

    public void test() {
        this.userService.list();
    }

    public void test2() {
        this.customService.custom();
    }
}
