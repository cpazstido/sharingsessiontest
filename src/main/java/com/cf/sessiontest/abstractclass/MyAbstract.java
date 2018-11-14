package com.cf.sessiontest.abstractclass;

public abstract class MyAbstract {
    /**
     * 不写public只能在同包下继承，写了public就可以在不同包下继承
     */
    public abstract void func();
}
