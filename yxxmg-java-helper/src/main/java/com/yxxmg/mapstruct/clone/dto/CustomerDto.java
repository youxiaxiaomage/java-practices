package com.yxxmg.mapstruct.clone.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/30
 */
@Data
public class CustomerDto {
    private Long id;
    private String customerName;
    private List<OrderItemDto> orders;
    private Map<OrderItemKeyDto, OrderItemDto> stock;
}
