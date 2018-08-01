package com.cf.sessiontest.aop;

import com.cf.sessiontest.model.User;
import com.cf.sessiontest.service.IUserService;
import com.cf.sessiontest.service.impl.UserServiceImpl;

public class TestAop {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = ProxyFactory.getInstance();
        //创建一个实现了UserService接口的对象
        IUserService userService = new UserServiceImpl();
        //创建一个基于userService对象的代理对象
        IUserService proxy = proxyFactory.create(userService);
        //调用代理对象的某个方法
        String re = proxy.testTransaction();
        System.out.println(re);
    }
}
