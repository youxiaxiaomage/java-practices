package com.yxxmg.mybatisplussample.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2022/11/7
 */
@Data
public class Words implements Serializable {
    private static final long serialVersionUID = -7301787766524495503L;
    @ExcelProperty("身份证")
    private String words;
}
