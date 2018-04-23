package com.cf.sessiontest.dao;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 公共数据库操作层
 * @author 郭胜凯
 * @time 2016年5月3日下午2:55:13
 * @email 719348277@qq.com
 */
public interface Dao<T> extends Mapper<T>, MySqlMapper<T> {
}