package com.yxxmg.gof.create.factory;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : DAO服务
 * @since : 2023/5/5
 */
public interface IDao<T> {
    /**
     * 保存
     *
     * @param t 对象
     * @return true:成功；false：失败
     */
    boolean save(T t);

    /**
     * 更新对象
     *
     * @param t 包装对象
     * @return 0:未更新到数据；≥1更新成功
     */
    int update(T t);

    /**
     * 删除对象
     *
     * @param t 请求对象
     * @return true:删除成功；false：删除失败
     */
    boolean delete(T t);

    /**
     * 根据主键查询
     *
     * @param id 主键ID
     * @return 对象
     */
    T selectById(String id);
}
