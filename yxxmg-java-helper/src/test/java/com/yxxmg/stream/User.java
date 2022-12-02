package com.yxxmg.stream;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 用户
 * @since : 2022/12/2
 */
@Data
@Accessors(chain = true)
public class User implements Serializable {
    private static final long serialVersionUID = -8397581123005389357L;
    private String id;
    private String userName;
    private String password;
    private int age;
}
