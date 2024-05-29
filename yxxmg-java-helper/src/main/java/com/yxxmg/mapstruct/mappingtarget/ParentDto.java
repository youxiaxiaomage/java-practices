package com.yxxmg.mapstruct.mappingtarget;

import java.util.List;

import lombok.Data;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/29
 */
@Data
public class ParentDto {
    private String name;
    private List<ChildDto> children;
}
