package com.yxxmg.mapstruct.convert;

import com.yxxmg.mapstruct.Address;
import com.yxxmg.mapstruct.DeliveryAddressDto;
import com.yxxmg.mapstruct.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/14
 */
@Mapper
public interface AddressMapper {
    AddressMapper MAPPER = Mappers.getMapper(AddressMapper.class);

    @Mapping(target = "description", source = "person.describe")
    @Mapping(target = "houseNumber", source = "address.houseNo")
    DeliveryAddressDto personAndAddressToDeliveryAddressDto(Person person, Address address);
}
