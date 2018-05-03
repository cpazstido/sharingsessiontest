package com.cf.sessiontest.service;

import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 通用接口
 */
@Service
public interface IService<T> {
    /**
     * 根据非空值获取数据
     * @param t
     * @return
     * @throws Exception
     */
    List<T> getNotNull(T t) ;
    /**
     * 根据非空值获取一条数据
     * @param t
     * @return
     */
    T getNotNullToOne(T t) ;
    /**
     * 根据id后去数据方法
     * @param key
     * @return
     */
    T getByKey(Object key) ;

    /**
     * 自定义查询方法
     * @param example
     * @return
     */
    List<T> getByExample(Object example) ;

    /**
     * 分页查询数据
     * @param size 一页有多少个数据
     * @param page 第几页
     * @return
     */
    List<T> getAllByPaging(Integer size, Integer page) ;
    /**
     * 根据查询条件分页数据
     * @param example 查询条件
     * @param size 一页有多少个数据
     * @param page 第几页
     * @return
     */
    List<T> getAllByPaging(Integer size, Integer page, Example example) ;
    /**
     * 根据查询条件分页数据
     * @param t 查询条件
     * @param size 一页有多少个数据
     * @param page 第几页
     * @return
     */
    List<T> getAllByPaging(Integer size, Integer page, T t) ;
    /**
     * 根据查询条件分页数据
     * @param example 查询条件
     * @param size 一页有多少个数据
     * @param page 第几页
     * @return
     */
    List<T> getAllByPaging(Integer size, Integer page, Example example, String columnNmae, boolean isDesc) ;
    /**
     * 根据条件统计数量
     * @param example
     * @return
     */
    public int getCountByExample(Object example);
    /**
     * 根据id删除数据方法
     * @param key
     * @return
     * @throws Exception
     */
    int delete(Object key) throws Exception;
    /**
     * 根据id删除数据方法
     * @param example 删除条件
     * @return
     * @throws Exception
     */
    int delete(Example example) throws Exception;

    /**
     * 更新实体类全部属性方法
     * @param entity
     * @return
     * @throws Exception
     */
    int updateAll(T entity) throws Exception;
    /**
     * 更新实体类非空方法
     * @param entity
     * @return
     * @throws Exception
     */
    int updateNotNull(T entity) throws Exception;
    /**
     * 更新实体类非空方法
     * @param entity
     * @param example 删除条件
     * @return
     * @throws Exception
     */
    int updateNotNull(T entity, Object example) throws Exception;

    /**
     * 数据保存方法
     * @param entity
     * @return
     * @throws Exception
     */
    int save(T entity) throws Exception;
    /**
     * 数据保存方法
     * @return
     * @throws Exception
     */
    int bathSave(List<T> entitys) throws Exception;

    //TODO 其他...
}