package com.yxxmg.mybatisplussample.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yxxmg.mybatisplussample.api.UserService;
import com.yxxmg.mybatisplussample.domain.User;
import com.yxxmg.mybatisplussample.dto.UserDTO;
import com.yxxmg.mybatisplussample.dto.UserQueryDTO;
import com.yxxmg.mybatisplussample.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 用户服务
 * @since : 2022/11/3
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public String add(UserDTO userDTO) {
        return save(UserDTO.of(userDTO)) ? "保存成功" : "保存失败";
    }

    @Override
    public String update(UserDTO userDTO) {
        return updateById(UserDTO.of(userDTO)) ? "修改成功" : "修改失败";
    }

    @Override
    public String valid(UserDTO userDTO) {
        List<User> userList = list(Wrappers.lambdaQuery(User.class)
            .ne(StringUtils.isNotBlank(userDTO.getUserId()), User::getUserId, userDTO.getUserId())
            .eq(User::getUserName, userDTO.getUserName()));
        return CollectionUtils.isNotEmpty(userList) ? "用户已存在" : "";
    }

    @Override
    @Cacheable(key = "#userQueryDTO.getPageNum() + #userQueryDTO.getPageSize() + #userQueryDTO.getUserName()",
        value = "userList")
    public PageInfo<User> list(UserQueryDTO userQueryDTO) {
        log.info("page query with request:{}", userQueryDTO);
        PageHelper.startPage(userQueryDTO.getPageNum(), userQueryDTO.getPageSize());
        return new PageInfo<>(list(Wrappers.lambdaQuery(User.class)
            .like(StringUtils.isNotBlank(userQueryDTO.getUserName()), User::getUserName, userQueryDTO.getUserName())));
    }

    @Override
    public String updateParam(UserDTO userDTO) {
        // 更新空字段
        boolean result = this.update(
            Wrappers.lambdaUpdate(User.class).set(User::getGender, null).eq(User::getUserId, userDTO.getUserId()));
        return result ? "更新成功" : "更新失败";
    }
}
