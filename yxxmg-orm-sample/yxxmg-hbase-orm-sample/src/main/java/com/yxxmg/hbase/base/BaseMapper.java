package com.yxxmg.hbase.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.yxxmg.hbase.conditions.Wrapper;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/19
 */
public interface BaseMapper<T> extends Mapper<T> {
    int insert(T entity);

    int deleteById(Serializable id);

    int deleteById(T entity);

    int deleteByMap(Map<String, Object> columnMap);

    int delete(Wrapper<T> queryWrapper);

    int updateById(T entity);

    int update(T entity, Wrapper<T> updateWrapper);

    T selectById(Serializable id);

    List<T> selectBatchIds(Collection<? extends Serializable> idList);
}
