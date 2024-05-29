package com.yxxmg.mapstruct;

import java.io.Serializable;
import java.util.List;

import com.yxxmg.mapstruct.convert.MenuMapper;

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

}
