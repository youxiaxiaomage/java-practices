package com.yxxmg.hbase.command;

import java.lang.reflect.Method;

import org.springframework.util.ObjectUtils;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/19
 */
public class DeleteCommand<T> extends AbstractCommand<T> {
    @Override
    public Object invoke(Method method, Object[] args) {
        if (!ObjectUtils.isEmpty(args)) {
            // Delete delete = new Delete(ByteUtil.);
        }
        return null;
    }
}
