package com.cf.sessiontest.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
    static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(atomicInteger.getAndIncrement());
                }
            }).start();
        }
//        System.out.println("exit");
    }
}
