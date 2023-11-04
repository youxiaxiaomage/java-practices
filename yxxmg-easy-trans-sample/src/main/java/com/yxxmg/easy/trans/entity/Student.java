package com.yxxmg.easy.trans.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fhs.core.trans.anno.Trans;
import com.fhs.core.trans.constant.TransType;
import com.fhs.core.trans.vo.TransPojo;

import lombok.Data;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/11/2
 */
@TableName("tbl_student")
@Data
public class Student implements TransPojo {
    @TableId("student_id")
    private String studentId;
    @Trans(type = TransType.DICTIONARY, key = "sex", ref = "sexName")
    @TableField("sex")
    private Integer sex;
    // 这个字段可以不写，实现了TransPojo接口后有一个getTransMap方法，sexName可以让前端去transMap取
    @TableField(exist = false)
    private String sexName;
    @Trans(type = TransType.SIMPLE, target = School.class, fields = "schoolName")
    private String schoolId;

    @Trans(type = TransType.RPC, targetClassName = "com.yxxmg.easy.trans.entity.School", serviceName = "easyTrans",
        fields = "schoolName", alias = "middle")
    private String middleId;

    // 枚举翻译，返回文科还是理科给前端
    @Trans(type = TransType.ENUM, key = "desc")
    @TableField("student_type")
    private StudentType studentType = StudentType.ARTS;

    public static enum StudentType {

        ARTS("文科"), SCIENCES("理科");

        private String desc;

        StudentType(String desc) {
            this.desc = desc;
        }
    }
}
