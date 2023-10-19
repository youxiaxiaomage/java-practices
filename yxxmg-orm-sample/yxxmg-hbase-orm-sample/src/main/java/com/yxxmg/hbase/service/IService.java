package com.yxxmg.hbase.service;

import com.yxxmg.hbase.base.BaseMapper;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : TODO
 * @since : 2023/10/19
 */
public interface IService<T> {
    BaseMapper<T> getBaseMapper();

    Class<T> getEntityClass();
}
