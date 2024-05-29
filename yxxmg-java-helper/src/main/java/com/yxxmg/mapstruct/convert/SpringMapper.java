package com.yxxmg.mapstruct.convert;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.yxxmg.mapstruct.Person;
import com.yxxmg.mapstruct.PersonDTO;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : 支持Spring类型扩展
 * @since : 2024/5/28
 */
@Mapper(componentModel = SPRING)
public interface SpringMapper {
    SpringMapper MAPPER = Mappers.getMapper(SpringMapper.class);

    PersonDTO personDoToDTO(Person person);
}
