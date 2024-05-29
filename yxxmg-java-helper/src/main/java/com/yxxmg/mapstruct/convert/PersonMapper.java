package com.yxxmg.mapstruct.convert;

import java.util.Date;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.yxxmg.mapstruct.Person;
import com.yxxmg.mapstruct.PersonDTO;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/28
 */
@Mapper(imports = Date.class)
public interface PersonMapper {
    PersonMapper MAPPER = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "personName", source = "name")
    @Mapping(target = "id", ignore = true)
    // 忽略id,不进行映射
    PersonDTO personDoToDTO(Person person);

    @Mapping(target = "personName", source = "name") // 指定映射
    @Mapping(target = "describe", source = "describe", defaultValue = "默认值") // 设置默认值
    @Mapping(target = "updateDate", expression = "java(new java.util.Date())") // 表达式 java
    @Mapping(target = "modifyDate", expression = "java(new Date())")
    @Mapping(target = "createDate", source = "createTime", dateFormat = "YYYY-MM-DD") // 格式化
    @Mapping(source = "source", target = "source", numberFormat = "#0.00")
    PersonDTO convert2(Person person);
}