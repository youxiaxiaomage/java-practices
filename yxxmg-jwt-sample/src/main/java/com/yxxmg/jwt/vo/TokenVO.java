package com.yxxmg.jwt.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/16
 */
@Data
@Builder
public class TokenVO implements Serializable {
    private String token;
}
