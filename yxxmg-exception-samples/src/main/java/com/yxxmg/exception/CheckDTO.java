package com.yxxmg.exception;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * @author : zhaoyan
 * @version : 1.0
 * @description :
 * @since : 2023/3/15
 */
@Data
public class CheckDTO implements Serializable {
    private static final long serialVersionUID = 2127898909335354636L;
    @NotBlank(message = "{name.not.null}")
    private String name;
}
