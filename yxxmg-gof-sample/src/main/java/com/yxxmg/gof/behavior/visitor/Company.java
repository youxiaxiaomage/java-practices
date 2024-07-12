package com.yxxmg.gof.behavior.visitor;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/7/12
 */
public interface Company {
    String create(Paper paper);

    String create(Copper copper);
}
