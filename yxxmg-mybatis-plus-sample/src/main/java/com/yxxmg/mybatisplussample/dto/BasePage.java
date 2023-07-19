package com.yxxmg.mybatisplussample.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 分页请求基类
 * @since : 2022/11/6
 */
@Data
public class BasePage implements Serializable {
    private static final long serialVersionUID = 3425382590971875810L;
    private static final Integer DEFAULT_PAGE_NUM = 1;
    private static final Integer DEFAULT_PAGE_SIZE = 10;
    @ApiModelProperty(value = "当前页")
    private Integer pageNum = DEFAULT_PAGE_NUM;
    @ApiModelProperty(value = "每页条数")
    private Integer pageSize = DEFAULT_PAGE_SIZE;
}
