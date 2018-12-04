package com.cf.sessiontest.service;

import com.cf.sessiontest.model.User;
/**
 * User服务接口类
 * @author cn.sofmit
 *
 */
public interface IUserService extends IService<User>{
    public String testTransaction();
    public User testTransaction1();

    User queryUserById(String userId);

    int updateUser(User user);

}
