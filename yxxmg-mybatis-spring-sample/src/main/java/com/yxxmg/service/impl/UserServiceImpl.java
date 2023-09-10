package com.yxxmg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yxxmg.entity.User;
import com.yxxmg.mapper.UserMapper;
import com.yxxmg.service.UserService;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/9/9
 */
@Service
public class UserServiceImpl implements UserService {
    // @Resource
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> list() {
        return this.userMapper.list();
    }

    @Override
    public List<User> listAll() {
        return this.userMapper.listAll();
    }
}
