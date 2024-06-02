package com.yxxmg.gof.behavior.interpreter;

import java.util.Map;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/15
 */
public class SubExpression extends AbstractExpression {
    public SubExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int interpreter(Map<String, Integer> variables) {
        return super.left.interpreter(variables) - super.right.interpreter(variables);
    }
}
