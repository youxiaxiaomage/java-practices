package com.yxxmg.mapstruct;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/14
 */
@Data
@Accessors(chain = true)
public class User implements Serializable {
    private static final long serialVersionUID = -6149751509309793406L;
    private String userId;
    private String userName;
    private Gender gender;
    private Status status;
}
