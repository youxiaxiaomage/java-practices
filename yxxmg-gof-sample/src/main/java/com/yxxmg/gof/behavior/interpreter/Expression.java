package com.yxxmg.gof.behavior.interpreter;

import java.util.Map;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/15
 */
public interface Expression {
    int interpreter(Map<String, Integer> variables);
}
