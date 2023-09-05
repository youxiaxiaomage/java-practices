package com.yxxmg.mybatisplussample.domain;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : ActiveRecord
 * @since : 2023/9/5
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Student extends Model<Student> {
    private static final long serialVersionUID = 5002216026290204766L;
    private String studentId;
    private String studentName;
}
