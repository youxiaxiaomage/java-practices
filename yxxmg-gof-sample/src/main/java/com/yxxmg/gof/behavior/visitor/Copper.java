package com.yxxmg.gof.behavior.visitor;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/7/12
 */
public class Copper implements Material {
    @Override
    public String accept(Company company) {
        return company.create(this);
    }
}
