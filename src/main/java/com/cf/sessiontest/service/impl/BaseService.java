package com.cf.sessiontest.service.impl;

import com.cf.sessiontest.dao.Dao;
import com.cf.sessiontest.service.IService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 通用服务类
 * @author Administrator
 *
 * @param <T>
 */
public abstract class BaseService<T> implements IService< T> {

    @Autowired
    protected Dao<T> mapper;
    @Override
    public T getByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }
    @Override
    public List<T> getNotNull(T t) {
        return mapper.select(t);
    };
    @Override
    public T getNotNullToOne(T t) {
        return mapper.selectOne(t);
    };
    @Override
    public int save(T entity) {
        int i = 1/0;
        return mapper.insert(entity);
    }
    @Override
    public int bathSave(List<T> entitys) throws Exception {
        // TODO Auto-generated method stub
        return mapper.insertList(entitys);
    }
    @Override
    public int delete(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }
    @Override
    public int delete( Example example) {
        return mapper.deleteByExample(example);
    }
    @Override
    public int updateAll(T entity) {
        return mapper.updateByPrimaryKey(entity);
    }
    @Override
    public int updateNotNull(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }
    @Override
    public int updateNotNull(T entity, Object example) throws Exception {
        return mapper.updateByExampleSelective(entity, example);
    };
    @Override
    public List<T> getByExample(Object example) {
        return mapper.selectByExample(example);
    }

    @Override
    public List<T> getAllByPaging(Integer size, Integer page) {
        // TODO Auto-generated method stub
        PageHelper.startPage(page, size);
        return mapper.selectAll();
    }
    @Override
    public List<T> getAllByPaging(Integer size, Integer page,T t){
        PageHelper.startPage(page, size);
        return mapper.select(t);
    };

    @Override
    public List<T> getAllByPaging(Integer size, Integer page, Example example) {
        PageHelper.startPage(page, size);
        return mapper.selectByExample(example);
    }

    @Override
    public List<T> getAllByPaging(Integer size, Integer page,Example example,String columnNmae,boolean isDesc){
        PageHelper.startPage(page, size);
        example.setOrderByClause(columnNmae+" desc");
        //if(isDesc){
        //    PageHelper.orderBy( columnNmae+" desc");
        //}else{
        //    PageHelper.orderBy(columnNmae);
        //}
        return mapper.selectByExample(example);
    };
    @Override
    public int getCountByExample(Object example) {
        // TODO Auto-generated method stub
        return mapper.selectCountByExample(example);
    }

}
