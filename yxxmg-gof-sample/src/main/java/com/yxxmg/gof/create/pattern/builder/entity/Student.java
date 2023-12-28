package com.yxxmg.gof.create.pattern.builder.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/5
 */
@Data
@Accessors(chain = true)
public class Student implements Serializable {
    private static final long serialVersionUID = 4099723881512840653L;
    private String id;
    private String name;
    private Integer age;
}
