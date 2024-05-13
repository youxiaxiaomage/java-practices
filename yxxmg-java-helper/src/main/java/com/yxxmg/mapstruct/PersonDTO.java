package com.yxxmg.mapstruct;

import lombok.Data;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/16
 */
@Data
public class PersonDTO implements Serializable {
    private static final long serialVersionUID = 1962392065741049494L;
    String describe;
    private Long id;
    private String personName;
    private String age;
    private String source;
    private String height;
    private Date updateDate;
    private Date modifyDate;
    private String createDate;

    public static PersonDTO convert(Person person) {
        return PersonMapper.MAPPER.personDoToDTO(person);
    }

    public static PersonDTO convert2(Person person) {
        return PersonMapper.MAPPER.convert2(person);
    }

    @Mapper(imports = Date.class)
    interface PersonMapper {
        PersonMapper MAPPER = Mappers.getMapper(PersonMapper.class);

        @Mapping(target = "personName", source = "name")
        @Mapping(target = "id", ignore = true) // 忽略id,不进行映射
        PersonDTO personDoToDTO(Person person);

        @Mapping(target = "personName", source = "name") // 指定映射
        @Mapping(target = "describe", source = "describe", defaultValue = "默认值") // 设置默认值
        @Mapping(target = "updateDate", expression = "java(new java.util.Date())") // 表达式 java
        @Mapping(target = "modifyDate", expression = "java(new Date())")
        @Mapping(target = "createDate", source = "createTime", dateFormat = "YYYY-MM-DD") // 格式化
        @Mapping(source = "source", target = "source", numberFormat = "#0.00")
        PersonDTO convert2(Person person);

    }
}