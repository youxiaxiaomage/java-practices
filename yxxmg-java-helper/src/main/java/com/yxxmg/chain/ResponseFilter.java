package com.yxxmg.chain;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : TODO
 * @since : 2023/6/19
 */
public class ResponseFilter implements Filter {
    @Override
    public String doFilter() {
        return this.getClass().getName() + " filter";
    }

    @Override
    public int priority() {
        return Integer.MAX_VALUE;
    }
}
