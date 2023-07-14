package com.yxxmg.gof.relation.interpreter;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/11
 */
@Data
@NoArgsConstructor
public abstract class AbstractExpression implements Expression {
    private Expression rightExpression;
    private Expression leftExpression;

    public AbstractExpression withRightExpression(Expression expression) {
        setRightExpression(expression);
        return this;
    }

    public AbstractExpression withLeftExpression(Expression expression) {
        setLeftExpression(expression);
        return this;
    }

    /**
     * 获取表达式
     * 
     * @return 表达式
     */
    public abstract String getExpression();

    public <E extends Expression> E getLeftExpression(Class<E> clazz) {
        return clazz.cast(getLeftExpression());
    }

    public <E extends Expression> E getRightExpression(Class<E> clazz) {
        return clazz.cast(getRightExpression());
    }
}
