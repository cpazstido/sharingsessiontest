package com.cf.sessiontest.thread;

import java.util.concurrent.locks.LockSupport;

public class ThreadA implements Runnable {
    @Override
    public void run() {

        try {
            for (;;){
                parkMethod();
            }
        } catch (Error e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally");
        }
    }

    private void parkMethod(){
        System.out.println("enter park");
        LockSupport.park(this);
        System.out.println("exit park");
    }
}
