package com.yxxmg.sensitive.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * @author : zhaoyan
 * @version : 1.0
 * @description :
 * @since : 2023/4/18
 */
@Data
public class UserTest implements Serializable {
    private static final long serialVersionUID = 1155777836340232660L;
    private String userName;
    private String userId;
}
