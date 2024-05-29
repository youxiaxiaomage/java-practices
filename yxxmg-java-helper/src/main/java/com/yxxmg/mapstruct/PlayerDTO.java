package com.yxxmg.mapstruct;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/17
 */
@Data
public class PlayerDTO implements Serializable {
    private static final long serialVersionUID = 9039704828265968138L;
    private String id;
    private String name;
    private List<GameDTO> games;


}
