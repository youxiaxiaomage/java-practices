package com.yxxmg.jetcache.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxxmg.jetcache.entity.User;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/3/28
 */
public interface UserService extends IService<User> {
    User getUserById(Long userId);

    void updateUser(User user);

    void deleteUser(Long userId);
}
