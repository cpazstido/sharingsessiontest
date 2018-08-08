package com.cf.sessiontest.test;

import com.cf.sessiontest.model.TestModel;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class TestConverter {

    public static void main(String[] args) {
        TestModel tm = new TestModel();
        BeanWrapper bw = new BeanWrapperImpl(tm);
        bw.setPropertyValue("good", "on");
        //bw.setPropertyValue("good", "1");
        //bw.setPropertyValue("good", "true");
        //bw.setPropertyValue("good", "yes");
        System.out.println(tm);
    }
}
