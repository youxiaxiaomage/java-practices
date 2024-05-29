package com.yxxmg.mapstruct.convert;

import java.util.List;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import com.yxxmg.mapstruct.Menu;
import com.yxxmg.mapstruct.MenuDTO;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : T
 * @since : 2024/5/28
 */
@Mapper
public interface MenuMapper {
    MenuMapper MAPPER = Mappers.getMapper(MenuMapper.class);

    @Mapping(target = "parentId", source = "parentId")
    Menu convert(String parentId, MenuDTO menuDTO);

    @IterableMapping(qualifiedByName = "convertDTO")
    List<Menu> convert(@Context String parentId, List<MenuDTO> menuDTOList);

    @Named("convertDTO")
    default Menu convertDTO(@Context String parentId, MenuDTO menuDTO) {
        return convert(parentId, menuDTO);
    }
}
