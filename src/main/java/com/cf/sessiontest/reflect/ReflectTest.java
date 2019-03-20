package com.cf.sessiontest.reflect;

import com.cf.sessiontest.model.Fruit;

public class ReflectTest {

    public void tt(Object o){
        System.out.println(o.getClass().getName());
    }

    public static void main(String[] args) {
        Fruit fruit = new Fruit();
        Class class1 = fruit.getClass();//方式一：通过对象的getClass()获取

        Class class2 = Fruit.class;//方式二：通过静态属性获取

        Class class3 = null;
        try {//方式三：通过类全限定名获取
            class3 = Class.forName("com.cf.sessiontest.service.IService");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(class3.isInterface());

        System.out.println(class1+" "+class2+" "+class3);
    }
}
