package com.cf.sessiontest.test;

import com.cf.sessiontest.model.User;
import com.cf.sessiontest.multidatasource.DataSourceTypeManager;
import com.cf.sessiontest.multidatasource.DataSources;
import com.cf.sessiontest.service.IUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.SQLException;

public class DBTest extends BaseJunit4Test {
//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    @Autowired
    private IUserService userService;

    @Test
    public void isDBOk(){
//        boolean flag = false;
//        long startTime=0,endTime=0;
//        try {
//            startTime = System.currentTimeMillis();
//            Connection con = jdbcTemplate.getDataSource().getConnection();
//            endTime = System.currentTimeMillis();
//            System.out.println((endTime-startTime)/1000);
//            flag = true;
//        } catch (SQLException e) {
//            endTime = System.currentTimeMillis();
//            System.out.println((endTime-startTime)/1000);
//            e.printStackTrace();
//        }
//        System.out.println(flag);
    }

    @Test
    public void addUser() throws Exception {
        User user = new User();
        user.setName("陈");
        user.setAge(29);
        userService.save(user);

        DataSourceTypeManager.set(DataSources.SLAVE);
        user = new User();
        user.setName("飞");
        user.setAge(30);
        userService.save(user);
        System.out.println("");
    }

    @Test
    public void testTransaction() throws Exception {

    }
}
