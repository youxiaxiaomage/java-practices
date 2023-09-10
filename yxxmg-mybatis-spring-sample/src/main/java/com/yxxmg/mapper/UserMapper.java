package com.yxxmg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yxxmg.entity.User;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/9/9
 */
// @Mapper
public interface UserMapper {
    /**
     * 查询全部用户
     * 
     * @return 用户列表
     */
    List<User> list();

    List<User> listAll();

    int insert(@Param("cm") User user);
}
