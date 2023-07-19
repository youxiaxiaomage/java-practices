package com.yxxmg.mybatisplussample.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yxxmg.mybatisplussample.enums.GenderEnum;

import lombok.Data;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 用户表
 * @since : 2022/11/3
 */
@Data
@TableName(value = "user", keepGlobalPrefix = true)
public class User implements Serializable {
    private static final long serialVersionUID = -4380276147877149672L;
    /**
     * 用户主键 ASSIGN_ID 默认雪花算法
     */
    @TableId(value = "USER_ID", type = IdType.ASSIGN_ID)
    private String userId;
    /**
     * 用户名
     */
    @TableField("USER_NAME")
    private String userName;
    /**
     * 性别
     */
    @TableField("GENDER")
    private GenderEnum gender;
    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}
