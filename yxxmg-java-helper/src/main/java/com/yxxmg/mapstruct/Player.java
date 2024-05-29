package com.yxxmg.mapstruct;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/17
 */
@Data
@Accessors(chain = true)
public class Player implements Serializable {
    private static final long serialVersionUID = -8959276361626208164L;
    private Long id;
    private String name;
    private List<Game> games;
}
