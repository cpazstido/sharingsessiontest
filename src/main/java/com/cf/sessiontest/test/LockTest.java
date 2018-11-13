package com.cf.sessiontest.test;

public class LockTest {
    public static void main(String[] args) {
        LockTest lockTest= new LockTest();
        lockTest.lock();
    }

    public void lock(){
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
