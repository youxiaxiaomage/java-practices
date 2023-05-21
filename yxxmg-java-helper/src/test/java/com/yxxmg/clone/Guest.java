package com.yxxmg.clone;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/21
 */
@Getter
@Setter
public class Guest implements Serializable {
    private static final long serialVersionUID = -6452950691190516563L;
    private Long id;
    private String name;
    private Attribute attribute;
}
