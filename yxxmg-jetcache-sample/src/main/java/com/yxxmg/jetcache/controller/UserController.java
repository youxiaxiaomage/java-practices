package com.yxxmg.jetcache.controller;

import org.springframework.web.bind.annotation.*;

import com.yxxmg.jetcache.api.UserService;
import com.yxxmg.jetcache.entity.User;

import lombok.RequiredArgsConstructor;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/3/28
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/getUserById/{userId}")
    public User getUserById(@PathVariable("userId") Long userId) {
        return this.userService.getUserById(userId);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable("userId") Long userId) {
        this.userService.deleteUser(userId);
        return "delete success";
    }

    @PutMapping("/updateUser")
    public String updateUser(@RequestBody User user) {
        this.userService.updateUser(user);
        return "update success";
    }
}
