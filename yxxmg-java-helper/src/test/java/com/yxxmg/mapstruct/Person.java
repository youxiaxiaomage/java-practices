package com.yxxmg.mapstruct;

import lombok.Data;
import lombok.experimental.Accessors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/16
 */
@Data
@Accessors(chain = true)
public class Person implements Serializable {
    private static final long serialVersionUID = 7792236881466528753L;
    private String id;
    private String name;
    private String describe;
    private int age;
    private BigDecimal source;
    private double height;
    private Date createTime;

}
