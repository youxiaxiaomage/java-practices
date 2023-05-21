package com.yxxmg.mapstruct;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/17
 */
@Data
public class GameDTO implements Serializable {
    private static final long serialVersionUID = 4897786770282008257L;
    private String id;
    private String name;
    private String desc;
    private String openTime;
}
