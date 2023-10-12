package com.yxxmg.asm.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/11
 */
@Data
public class Employee implements Serializable {
    private String employeeId;
    private String employeeName;

    public void working() {
        System.out.println(employeeName + "正在上班");
    }
}
