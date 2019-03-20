package com.cf.sessiontest.model;

public class B extends A {

//    @Override

    /**
     * 私有方法不能被继承重写，但子类可以有与父类同名的私有方法
     */
    private void test(){

    }

    @Override
    public void a() {
        super.a();
    }
}
