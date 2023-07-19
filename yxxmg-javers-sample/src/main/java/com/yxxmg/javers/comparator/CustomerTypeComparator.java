package com.yxxmg.javers.comparator;

import com.yxxmg.javers.CustomerTypeEnum;
import org.javers.core.diff.custom.CustomValueComparator;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/6/9
 */
public class CustomerTypeComparator implements CustomValueComparator<CustomerTypeEnum> {
    @Override
    public boolean equals(CustomerTypeEnum a, CustomerTypeEnum b) {
        return a.getCode() == b.getCode();
    }

    @Override
    public String toString(CustomerTypeEnum value) {
        return value.getMsg();
    }
}
