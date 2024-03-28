package com.yxxmg.jetcache.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/3/28
 */
@Data
@TableName("TBL_USER")
public class User implements Serializable {
    private static final long serialVersionUID = 4303474557633216157L;
    private Long userId;
    private String userName;
}
