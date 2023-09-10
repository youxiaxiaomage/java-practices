package com.yxxmg.entity;

import java.io.Serializable;

import com.yxxmg.enums.Gender;

import lombok.Data;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/9/9
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -214513823340910172L;
    private String userId;
    private String userName;
    private String nickName;
    private String email;
    private Gender gender;
    private Integer age;
    private String address;
    private TeacherRef teacherId;
    private Dept dept;
}
