package com.cf.sessiontest.utils;

public class PropertyUtils {

    public PropertyUtils(){
        System.out.println("构造方法");
    }

    {
        System.out.println("每次new对象时，在构造方法前调用");
    }

    static {
        System.out.println("init properties in static");
    }

    public static String getProperties(String key){
        return "hello";
    }
}
