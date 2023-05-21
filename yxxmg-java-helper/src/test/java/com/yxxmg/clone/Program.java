package com.yxxmg.clone;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/21
 */
@Setter
@Getter
@Accessors(chain = true)
public class Program implements Serializable {
    private static final long serialVersionUID = 1735726156104341923L;
    private Long id;
    private String name;
    boolean isFee;
    private List<Guest> guestList;
}
