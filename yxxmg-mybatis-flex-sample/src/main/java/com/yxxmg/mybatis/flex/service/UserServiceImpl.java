package com.yxxmg.mybatis.flex.service;

import static com.yxxmg.mybatis.flex.entity.table.UserTableDef.USER;

import java.io.Serializable;
import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.CacheableServiceImpl;
import com.yxxmg.mybatis.flex.api.UserService;
import com.yxxmg.mybatis.flex.entity.User;
import com.yxxmg.mybatis.flex.mapper.UserMapper;

/**
 * @author : zhaoyan
 * @version : 1.0
 * @description :
 * @since : 2023/8/31
 */
@Service
@CacheConfig(cacheNames = "user")
public class UserServiceImpl extends CacheableServiceImpl<UserMapper, User> implements UserService {
    @Override
    public List<User> list(User user) {
        QueryWrapper queryWrapper = QueryWrapper.create().select().from(USER)
            .where(USER.USER_NAME.like(user.getUserName())).orderBy(USER.PK_USER.desc());
        return list(queryWrapper);
    }

    @Override
    @Cacheable(key = "#id")
    public User getById(Serializable id) {
        return super.getById(id);
    }
}
