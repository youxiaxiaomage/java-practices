package com.yxxmg.mybatisplussample.service;

import com.yxxmg.mybatisplussample.api.CustomService;
import com.yxxmg.mybatisplussample.api.UserService;
import com.yxxmg.mybatisplussample.domain.User;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2022/11/7
 */
@Named
@Slf4j
public class CustomServiceImpl implements CustomService {
    private UserService userService;

    @Inject
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> custom() {
        return this.userService.list();
    }
}
