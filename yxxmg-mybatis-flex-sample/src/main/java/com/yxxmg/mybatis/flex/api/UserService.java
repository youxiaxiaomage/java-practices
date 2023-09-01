package com.yxxmg.mybatis.flex.api;

import java.util.List;

import com.mybatisflex.core.service.IService;
import com.yxxmg.mybatis.flex.entity.User;

/**
 * @author : zhaoyan
 * @version : 1.0
 * @description :
 * @since : 2023/8/31
 */
public interface UserService extends IService<User> {
    List<User> list(User user);
}
