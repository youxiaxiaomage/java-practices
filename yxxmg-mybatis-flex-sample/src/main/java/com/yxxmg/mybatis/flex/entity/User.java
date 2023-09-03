package com.yxxmg.mybatis.flex.entity;

import java.io.Serializable;
import java.util.Date;

import com.mybatisflex.annotation.*;
import com.mybatisflex.core.keygen.KeyGenerators;
import com.mybatisflex.core.mask.Masks;
import com.yxxmg.mybatis.flex.enums.SexEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : zhaoyan
 * @version : 1.0
 * @description :
 * @since : 2023/8/31
 */
@Table("TEST_USER")
@Data
@ApiModel("用户")
public class User implements Serializable {
    private static final long serialVersionUID = -3201244095455658885L;
    @ApiModelProperty("主键")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    @Column("PK_USER")
    private String pkUser;
    /**
     * 字段脱敏
     */
    @Column("USER_NAME")
    @ApiModelProperty("用户名")
    @ColumnMask(Masks.CHINESE_NAME)
    private String userName;

    @ApiModelProperty("年龄")
    @Column("AGE")
    private Integer age;
    /**
     * 自定义字段脱敏
     */
    @ApiModelProperty("地址")
    @Column("ADDRESS")
    @ColumnMask("custom")
    private String address;
    /**
     * 逻辑删除
     */
    @Column(isLogicDelete = true)
    private Boolean isDeleted;

    /**
     * 乐观锁
     */
    @Column(version = true)
    private Long version;

    /**
     * 填充字段
     */
    @Column(onInsertValue = "now()")
    private Date createTime;
    @Column("SEX")
    private SexEnum sex;
}
