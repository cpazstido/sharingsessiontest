package com.cf.sessiontest.service.impl;

import com.cf.sessiontest.dao.UserMapper;
import com.cf.sessiontest.utils.ApplicationContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cf.sessiontest.model.User;
import com.cf.sessiontest.service.IUserService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;


/**
 * User服务接口实现类
 * @author cn.sofmit
 *
 */
@Service
public class UserServiceImpl extends BaseService<User> implements IUserService{
    @Autowired
    private UserMapper userMapper;

    @PostConstruct
    public IUserService getProxy() {
        //从Spring上下文中获取AOP代理对象
//        return ApplicationContextUtil.getApplicationContext().getBean(IUserService.class);
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String testTransaction(){
//        for(int i=0;i<8;i++){
//            User user = getProxy().testTransaction1();
//            System.out.println(user);
//        }
        return "hello";
    };

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.NOT_SUPPORTED)
    public User testTransaction1() {
        User user = userMapper.selectByPrimaryKey("6be8c36aaaf44926a1727a621e15d1c8");
        return user;
    }
}

