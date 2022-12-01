package com.yxxmg.mybatisplussample.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.yxxmg.mybatisplussample.domain.User;
import com.yxxmg.mybatisplussample.dto.UserDTO;
import com.yxxmg.mybatisplussample.dto.UserQueryDTO;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2022/11/3
 */
public interface UserService extends IService<User> {
    /**
     * 新增
     *
     * @param userDTO 请求对象
     * @return 成功失败
     */
    String add(UserDTO userDTO);

    /**
     * 修改
     *
     * @param userDTO 请求对象
     * @return 成功失败
     */
    String update(UserDTO userDTO);

    /**
     * 校验
     *
     * @param userDTO 请求对象
     * @return 成功失败
     */
    String valid(UserDTO userDTO);

    /**
     * 分页用户列表 如果觉得分页返回参数过多，也可以自己封装一个分页返回对象
     *
     * @param userQueryDTO 封装请求参数
     * @return 分页用户列表
     */
    PageInfo<User> list(UserQueryDTO userQueryDTO);
}
