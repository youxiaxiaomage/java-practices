package com.yxxmg.mapstruct.convert;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.yxxmg.mapstruct.BaseEnum;
import com.yxxmg.mapstruct.User;
import com.yxxmg.mapstruct.UserDTO;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/28
 */
@Mapper
public interface UserDTOMapper {
    UserDTOMapper MAPPER = Mappers.getMapper(UserDTOMapper.class);

    List<UserDTO> convert(List<User> userList);

    // 映射器中自定义方法
    default String convertEnum2Desc(BaseEnum baseEnum) {
        if (Objects.isNull(baseEnum)) {
            return StringUtils.EMPTY;
        }
        return baseEnum.desc();
    }
}
