package com.cf.sessiontest.dao;

import com.cf.sessiontest.model.User;
import org.apache.ibatis.annotations.Param;

/**
*  UserMapper
* @author cn.sofmit
*/
public interface UserMapper extends Dao<User> {
    int updateUserAge(@Param("age")int age);
}




