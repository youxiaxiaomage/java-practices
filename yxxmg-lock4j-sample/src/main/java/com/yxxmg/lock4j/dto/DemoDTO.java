package com.yxxmg.lock4j.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/6/20
 */
@Data
public class DemoDTO implements Serializable {
    private static final long serialVersionUID = 3862179972043037795L;
    private String userName;
    private String password;
}
