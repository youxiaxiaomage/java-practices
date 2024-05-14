package com.yxxmg.mapstruct;

import java.io.Serializable;

import lombok.Data;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/14
 */
@Data
public class DeliveryAddressDto implements Serializable {
    private static final long serialVersionUID = 4724201502225781717L;
    private String description;
    private String houseNumber;
}
