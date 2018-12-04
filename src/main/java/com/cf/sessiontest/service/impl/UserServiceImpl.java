package com.cf.sessiontest.service.impl;

import com.cf.sessiontest.dao.UserMapper;
import com.cf.sessiontest.myexception.MyException;
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
 *
 * @author cn.sofmit
 */
@Service
public class UserServiceImpl extends BaseService<User> implements IUserService {
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
    public String testTransaction() {
//        for(int i=0;i<8;i++){
//            User user = testTransaction1();
//            System.out.println(user);
//        }
        return "hello";
    }

    ;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NOT_SUPPORTED)
    public User testTransaction1() {
        User user = userMapper.selectByPrimaryKey("6be8c36aaaf44926a1727a621e15d1c8");
        return user;
    }

    @Override
    public User queryUserById(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    /**
     * 即：在只配置了@Transactional时以下两种异常会回滚：
     * 1、RuntimeException及其子类
     * 2、Error及其子类
     * 其它的受检异常将不会回滚
     *
     * 即：在配置了rollbackRules例如：
     * @Transactional(rollbackFor = MyException.class)
     * 1、事务会在抛出MyException异常时回滚，抛出其他非运行时异常，如Exception异常时不会回滚，他是根据异常类的全类名来判断的。
     * 2、并且，如果抛出的是RuntimeException、Error及他们的子类也会回滚(会走默认判断)
     *
     * rollbackFor可以配置多个异常，如
     * @Transactional(rollbackFor = {MyException.class，Exception.class})
     */
    @Transactional(rollbackFor = MyException.class)
    @Override
    public int updateUser(User user) throws Exception {
        int i = 1;
        int count = userMapper.updateByPrimaryKey(user);
        if (i == 1) {
            throw new Exception("a");
        }
        return count;
    }
}

