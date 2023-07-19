package com.yxxmg.mybatisplussample.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 用户查询DTO
 * @since : 2022/11/6
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("用户列表查询DTO")
public class UserQueryDTO extends BasePage implements Serializable {
    private static final long serialVersionUID = 5990395206235642031L;
    @ApiModelProperty(value = "用户名")
    private String userName;
}
