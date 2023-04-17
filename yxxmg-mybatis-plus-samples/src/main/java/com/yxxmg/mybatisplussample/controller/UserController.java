package com.yxxmg.mybatisplussample.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.yxxmg.mybatisplussample.api.UserService;
import com.yxxmg.mybatisplussample.domain.User;
import com.yxxmg.mybatisplussample.dto.UserDTO;
import com.yxxmg.mybatisplussample.dto.UserQueryDTO;
import com.yxxmg.mybatisplussample.validator.AddGroup;
import com.yxxmg.mybatisplussample.validator.UpdateGroup;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description
 * @since : 2022/11/3
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Api(value = "用户", tags = "用户接口")
public class UserController {
    private final UserService userService;

    @PostMapping("/add")
    @ApiOperationSupport(ignoreParameters = "userId")
    @ApiOperation("用户新增")
    public String add(@RequestBody @Validated(AddGroup.class) UserDTO userDTO) {
        return this.userService.add(userDTO);
    }

    @PutMapping("/update")
    @ApiOperation("用户修改")
    public String update(@RequestBody @Validated(UpdateGroup.class) UserDTO userDTO) {
        return this.userService.update(userDTO);
    }

    @PutMapping("/updateParam")
    @ApiOperation("用户修改-测试null")
    public String updateParam(@RequestBody UserDTO userDTO) {
        return this.userService.updateParam(userDTO);
    }

    @PostMapping("/list")
    @ApiOperation("用户列表")
    public PageInfo<User> list(@RequestBody UserQueryDTO userQueryDTO) {
        return this.userService.list(userQueryDTO);
    }
}
