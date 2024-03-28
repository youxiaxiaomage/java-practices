package com.yxxmg.jetcache.service;

import org.springframework.stereotype.Service;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CacheUpdate;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxxmg.jetcache.api.UserService;
import com.yxxmg.jetcache.entity.User;
import com.yxxmg.jetcache.mapper.UserMapper;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/3/28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    @Cached(name = "userCache", key = "#userId", expire = 36000, cacheType = CacheType.REMOTE)
    public User getUserById(Long userId) {
        return getById(userId);
    }

    @Override
    @CacheUpdate(name = "userCache", key = "#user.userId", value = "#user")
    public void updateUser(User user) {
        updateById(user);
    }

    @Override
    @CacheInvalidate(name = "userCache", key = "#userId")
    public void deleteUser(Long userId) {
        removeById(userId);
    }
}
