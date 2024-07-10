package com.yxxmg.flowable.interceptor;

import org.flowable.engine.impl.delegate.invocation.DefaultDelegateInterceptor;
import org.flowable.engine.impl.delegate.invocation.DelegateInvocation;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/7/10
 */
public class YxxmgDefaultDelegateInterceptor extends DefaultDelegateInterceptor {
    @Override
    public void handleInvocation(DelegateInvocation invocation) {
        super.handleInvocation(invocation);
    }
}
