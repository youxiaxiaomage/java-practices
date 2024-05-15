package com.yxxmg.gof.behavior.interpreter;

import java.util.Map;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/15
 */
public class VarExpression implements Expression {
    private String key;

    public VarExpression(String key) {
        this.key = key;
    }

    @Override
    public int interpreter(Map<String, Integer> variables) {
        return variables.get(key);
    }
}
