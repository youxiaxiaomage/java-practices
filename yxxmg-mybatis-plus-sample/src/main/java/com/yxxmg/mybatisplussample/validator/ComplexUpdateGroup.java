package com.yxxmg.mybatisplussample.validator;

import javax.validation.GroupSequence;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/12/21
 */
@GroupSequence({UpdateGroup.class, UpdateExpensiveGroup.class})
public interface ComplexUpdateGroup {}
