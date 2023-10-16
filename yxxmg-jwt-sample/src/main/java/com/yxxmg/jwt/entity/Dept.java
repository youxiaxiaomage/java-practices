package com.yxxmg.jwt.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : TODO
 * @since : 2023/10/16
 */
@Data
public class Dept implements Serializable {
    private String id;
    private String deptName;
}
