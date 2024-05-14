package com.yxxmg.mapstruct;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/14
 */
@Data
@Accessors(chain = true)
public class Address implements Serializable {
    private static final long serialVersionUID = 3843691807668486407L;
    private String houseNo;
}
