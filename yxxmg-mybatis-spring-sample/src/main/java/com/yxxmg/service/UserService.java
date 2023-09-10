package com.yxxmg.service;

import java.util.List;

import com.yxxmg.entity.User;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/9/9
 */
public interface UserService {
    List<User> list();

    List<User> listAll();
}
