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
public class Teacher implements Serializable {
    private static final long serialVersionUID = 3111272238525103919L;
    private String teacherId;
    private String teacherName;
}
