package com.yxxmg.drools.samples.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2022/12/5
 */
@Data
public class Person implements Serializable {
    private static final long serialVersionUID = 2746794872572039133L;
    private String name;
    private int age;
    private int sex;
    private String drlType;
    private boolean enablePlay;
}
