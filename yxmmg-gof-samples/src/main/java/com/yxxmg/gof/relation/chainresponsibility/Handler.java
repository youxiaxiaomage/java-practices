package com.yxxmg.gof.relation.chainresponsibility;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/10
 */
public interface Handler {
    /**
     * 设置下个处理器
     * 
     * @param nextHandler 下个处理器
     */
    void setNextHandler(Handler nextHandler);

    /**
     * 处理
     * 
     * @param days 天数
     * @return 成功还是失败
     */
    boolean handler(int days);
}
