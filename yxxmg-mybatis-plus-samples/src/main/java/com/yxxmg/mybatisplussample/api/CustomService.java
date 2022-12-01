package com.yxxmg.mybatisplussample.api;

import com.yxxmg.mybatisplussample.domain.User;

import java.util.List;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 自定义服务
 * @since : 2022/11/7
 */
public interface CustomService {
    /**
     * 自定义方法
     * 
     * @return 用户列表
     */
    List<User> custom();
}
