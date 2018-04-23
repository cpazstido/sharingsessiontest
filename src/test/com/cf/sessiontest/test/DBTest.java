package com.cf.sessiontest.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.SQLException;

public class DBTest extends BaseJunit4Test {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void isDBOk(){
        boolean flag = false;
        long startTime=0,endTime=0;
        try {
            startTime = System.currentTimeMillis();
            Connection con = jdbcTemplate.getDataSource().getConnection();
            endTime = System.currentTimeMillis();
            System.out.println((endTime-startTime)/1000);
            flag = true;
        } catch (SQLException e) {
            endTime = System.currentTimeMillis();
            System.out.println((endTime-startTime)/1000);
            e.printStackTrace();
        }
        System.out.println(flag);
    }
}
