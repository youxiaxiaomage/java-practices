package com.yxxmg.mapstruct;

import java.io.Serializable;

import lombok.Data;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : Mapping nested bean properties to current target 将嵌套Bean映射到当前目标上
 * @since : 2024/5/14
 */
@Data
public class Customer implements Serializable {
    private static final long serialVersionUID = -3523573904839833181L;
    private String customerId;
}
