package com.yxxmg.flink.cdc.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/8/19
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 7157114608942834455L;
    private String userId;
    private String userName;
    private String password;
}
