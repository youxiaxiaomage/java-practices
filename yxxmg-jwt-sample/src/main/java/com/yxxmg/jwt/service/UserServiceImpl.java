package com.yxxmg.jwt.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxxmg.jwt.api.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.yxxmg.jwt.entity.User;
import com.yxxmg.jwt.mapper.UserMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserDetailsService , UserService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getOne(Wrappers.lambdaQuery(User.class).eq(User::getUsername, username));
        return Optional.ofNullable(user).orElseThrow(()-> new UsernameNotFoundException("用户不存在"));
    }
}
