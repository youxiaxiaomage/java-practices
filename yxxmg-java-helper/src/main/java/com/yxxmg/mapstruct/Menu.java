package com.yxxmg.mapstruct;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/13
 */
@Data
public class Menu implements Serializable {
    private static final long serialVersionUID = -6954669293783252636L;
    private String menuId;
    private String menuName;
    private String parentId;
}
