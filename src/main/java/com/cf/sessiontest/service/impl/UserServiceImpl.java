package com.cf.sessiontest.service.impl;

import org.springframework.stereotype.Service;
import com.cf.sessiontest.model.User;
import com.cf.sessiontest.service.IUserService;
import org.springframework.transaction.annotation.Transactional;


/**
 * User服务接口实现类
 * @author cn.sofmit
 *
 */
@Service
public class UserServiceImpl extends BaseService<User> implements IUserService{

    @Transactional(rollbackFor = NullPointerException.class)
    @Override
    public String testTransaction(){
        return "hello";
    };

    @Override
    public void testTransaction1() {

    }
}

