package com.cf.sessiontest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import java.sql.Connection;
import java.sql.SQLException;

@Controller
public class Test {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
//        Test test = new Test();
//        System.out.println(test.isDBOk());
        Integer a = 5;
        Integer b = new Integer(5);
        System.out.println(a==b);
    }

    public boolean isDBOk(){
        boolean flag = false;
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
