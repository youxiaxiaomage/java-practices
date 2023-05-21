package com.yxxmg.mapstruct;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/17
 */
@Data
@Accessors(chain = true)
public class Game implements Serializable {
    private static final long serialVersionUID = 1243461723105496674L;
    private String id;
    private String name;
    private String desc;
    private Date openTime;
}
