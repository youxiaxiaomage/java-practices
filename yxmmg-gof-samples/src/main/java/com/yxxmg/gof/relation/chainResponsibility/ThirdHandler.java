package com.yxxmg.gof.relation.chainResponsibility;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/10
 */
public class ThirdHandler implements Handler {
    private static final int ALLOW_MAX_DAYS = 15;
    private Handler next;

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.next = nextHandler;
    }

    @Override
    public boolean handler(int days) {
        if (days <= ALLOW_MAX_DAYS) {
            System.out.println("总经理审核");
            return true;
        }
        System.out.println("审核天数大于15天，总经理无权限审核");
        System.out.println("转下一个人审核");
        return this.next.handler(days);
    }
}
