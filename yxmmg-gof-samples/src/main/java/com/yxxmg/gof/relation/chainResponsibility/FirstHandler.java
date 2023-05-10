package com.yxxmg.gof.relation.chainResponsibility;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/10
 */
public class FirstHandler implements Handler {
    private static final int ALLOW_MAX_DAYS = 3;
    private Handler next;

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.next = nextHandler;
    }

    @Override
    public boolean handler(int days) {
        if (days <= ALLOW_MAX_DAYS) {
            System.out.println("组长审批");
            return true;
        }
        System.out.println("审核天数大于3天，组长无权限审核");
        System.out.println("转下一个人审核");
        return next.handler(days);
    }
}
