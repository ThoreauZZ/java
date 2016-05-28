package com.henry.mongo.base;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * Created by zhaozhou on 16/5/25.
 */
public interface BaseMongoDao <T> {
    /**
     * 通过条件查询实体(集合)
     *
     * @return
     */
    public List<T> findAllObjects();

    /**
     * @param query
     * @return
     * @Description 查询所有实体
     * @author zhaozhou
     * @date 2015年11月10日 上午11:59:32
     */
    public List<T> findAll(Query query);

    /**
     * @param query
     * @return
     * @Description 通过一定的条件查询一个实体
     * @author zhaozhou
     */
    public T findOne(Query query);

    /**
     * @param query
     * @param update
     * @return 实体
     * @Description 通过条件查询更新数据
     */
    public T update(Query query, Update update);

    /**
     * @param entity
     * @return
     * @Description 保存一个对象到mongodb
     * @author yuanchangjun
     * @date 2015年11月10日 下午12:04:05
     */
    public T save(T entity);

    /**
     * @param id
     * @return
     * @Description 通过ID获取记录
     */
    public T findById(String id);

    /**
     * @Description 查找并且删除
     */
    public T findAndRemove(Query query);
}
