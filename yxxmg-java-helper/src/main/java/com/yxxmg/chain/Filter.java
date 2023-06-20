package com.yxxmg.chain;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/6/19
 */
public interface Filter {
    String doFilter();

    int priority();
}
