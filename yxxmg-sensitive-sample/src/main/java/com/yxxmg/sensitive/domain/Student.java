package com.yxxmg.sensitive.domain;

import java.io.Serializable;

import com.yxxmg.enums.Sensitive;
import com.yxxmg.enums.SensitiveStrategy;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2022/12/8
 */
@Data
@Accessors(chain = true)
public class Student implements Serializable {
    private static final long serialVersionUID = 1270380814231996333L;
    private String userId;
    @Sensitive(strategy = SensitiveStrategy.PHONE)
    private String phoneNumber;
    @Sensitive(strategy = SensitiveStrategy.EMAIL)
    private String email;
}
