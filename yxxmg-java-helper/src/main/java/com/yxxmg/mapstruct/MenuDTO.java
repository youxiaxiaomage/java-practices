package com.yxxmg.mapstruct;

import java.io.Serializable;
import java.util.List;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/13
 */
@Data
@Accessors(chain = true)
public class MenuDTO implements Serializable {
    private static final long serialVersionUID = -4103907135888246004L;
    private String menuId;
    private String menuName;
    /**
     * 父主键
     */
    private String parentId;

    public static List<Menu> convert(String parentId, List<MenuDTO> menuDTOList) {
        return MenuMapper.MAPPER.convert(parentId, menuDTOList);
    }

    @Mapper
    interface MenuMapper {
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

}
