package com.yxxmg.clone;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/21
 */
@Getter
@Setter
public class Attribute implements Serializable {
    private static final long serialVersionUID = -5989359440370274771L;
    private Sex sex;
    private Integer age;
    private String mail;
}
