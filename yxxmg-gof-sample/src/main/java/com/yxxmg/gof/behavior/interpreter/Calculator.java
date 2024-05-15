package com.yxxmg.gof.behavior.interpreter;

import java.util.Map;
import java.util.Stack;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/15
 */
public class Calculator {
    private Expression expression;

    public Calculator(String expStr) {
        Stack<Expression> stack = new Stack<>();
        char[] charArray = expStr.toCharArray();
        Expression left;
        Expression right;
        for (int i = 0; i < charArray.length; i++) {
            switch (charArray[i]) {
                case '+':
                    left = stack.pop();
                    right = new VarExpression(String.valueOf(charArray[++i]));
                    stack.push(new AddExpression(left, right));
                    break;
                case '-':
                    left = stack.pop();
                    right = new VarExpression(String.valueOf(charArray[++i]));
                    stack.push(new SubExpression(left, right));
                    break;
                default:
                    stack.push(new VarExpression(String.valueOf(charArray[i])));
            }
        }
        this.expression = stack.pop();
    }

    public int run(Map<String, Integer> variables) {
        return this.expression.interpreter(variables);
    }
}
