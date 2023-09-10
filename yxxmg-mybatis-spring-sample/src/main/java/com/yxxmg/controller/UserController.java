package com.yxxmg.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yxxmg.entity.User;
import com.yxxmg.service.UserService;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/9/9
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/list")
    public List<User> list() {
        return this.userService.list();
    }

    @GetMapping("/listAll")
    public List<User> listAll() {
        return this.userService.listAll();
    }
}
