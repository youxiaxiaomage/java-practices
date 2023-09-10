package com.yxxmg.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/9/10
 */
@Data
public class Dept implements Serializable {
    private static final long serialVersionUID = -4906488383991798789L;
    private String deptId;
    private String deptName;
}
