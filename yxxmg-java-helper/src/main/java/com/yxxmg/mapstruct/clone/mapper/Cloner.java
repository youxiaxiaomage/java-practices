package com.yxxmg.mapstruct.clone.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

import com.yxxmg.mapstruct.clone.dto.CustomerDto;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/30
 */
@Mapper(mappingControl = DeepClone.class)
public interface Cloner {
    Cloner MAPPER = Mappers.getMapper(Cloner.class);

    CustomerDto clone(CustomerDto customerDto);
}
