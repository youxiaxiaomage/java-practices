package com.yxxmg.javers;

import com.google.common.collect.Sets;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/6/12
 */
@Data
public class DataCompare<T> implements Serializable {
    private static final long serialVersionUID = -1223448352971587416L;
    private Set<T> addSet;
    private Set<T> removedSet;
    private Set<T> changeSet;

    public DataCompare() {
        this.addSet = Sets.newHashSet();
        this.changeSet = Sets.newHashSet();
        this.removedSet = Sets.newHashSet();
    }
}
