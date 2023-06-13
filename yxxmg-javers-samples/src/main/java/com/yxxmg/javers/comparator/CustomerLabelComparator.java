package com.yxxmg.javers.comparator;

import com.yxxmg.javers.CustomerLabel;
import org.javers.core.diff.custom.CustomValueComparator;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/6/9
 */
public class CustomerLabelComparator implements CustomValueComparator<CustomerLabel> {
    @Override
    public boolean equals(CustomerLabel a, CustomerLabel b) {
        return a.getId().equals(b.getId());
    }

    @Override
    public String toString(CustomerLabel value) {
        return value.getLabelName();
    }
}
