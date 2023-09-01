package com.yxxmg.mybatis.flex.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.yxxmg.mybatis.flex.api.UserService;
import com.yxxmg.mybatis.flex.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * @author : zhaoyan
 * @version : 1.0
 * @description :
 * @since : 2023/8/31
 */
@Api(value = "用户接口", tags = "用户接口")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    @ApiOperation("列表")
    public List<User> list(User user) {
        return this.userService.list(user);
    }

    @PostMapping
    @ApiOperation("新增")
    public String add(@RequestBody @Validated User user) {
        this.userService.save(user);
        return "success";
    }

    @PutMapping
    @ApiOperation("修改")
    public String update(@RequestBody User user) {
        this.userService.updateById(user);
        return "success";
    }

    @DeleteMapping
    @ApiOperation("删除")
    public String delete(@RequestParam String userId) {
        this.userService.removeById(userId);
        return "success";
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") String id) {
        return this.userService.getById(id);
    }

}
