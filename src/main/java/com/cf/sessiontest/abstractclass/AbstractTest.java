package com.cf.sessiontest.abstractclass;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class AbstractTest {
    public static void main(String[] args) {
        Method m =MyAbstract.class.getDeclaredMethods()[0];
        System.out.println(m.getName()+", "+ Modifier.isPublic(m.getModifiers()));
    }
}
