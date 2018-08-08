package com.cf.sessiontest.controller;

import com.cf.sessiontest.model.TestModel;
import com.cf.sessiontest.utils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

@Controller
public class Test {
//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
//        Test test = new Test();
//        System.out.println(test.isDBOk());

        BigDecimal a = new BigDecimal(0.1);
        BigDecimal b = new BigDecimal(0.2);
        System.out.println(a.compareTo(b));

        PropertyUtils.getProperties("");
        PropertyUtils.getProperties("");
        PropertyUtils.getProperties("");
        PropertyUtils.getProperties("");
        new PropertyUtils();
    }

    public boolean isDBOk(){
//        boolean flag = false;
//        try {
//            Connection con = jdbcTemplate.getDataSource().getConnection();
//            flag = true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return false;
    }

    @RequestMapping("/test/test1")
//    @ResponseBody
    public String getTest(Integer str1,String str2,TestModel testModel){
        return "success:"+str1+" "+str2+" "+testModel.toString();
    }

}
