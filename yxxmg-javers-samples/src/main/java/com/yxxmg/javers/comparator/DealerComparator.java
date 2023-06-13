package com.yxxmg.javers.comparator;

import com.yxxmg.javers.Dealer;
import org.javers.core.diff.custom.CustomValueComparator;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/6/9
 */
public class DealerComparator implements CustomValueComparator<Dealer> {
    @Override
    public boolean equals(Dealer a, Dealer b) {
        return a.getDealerId().equals(b.getDealerId());
    }

    @Override
    public String toString(Dealer value) {
        return value.getDearName();
    }
}
