package com.cf.sessiontest.test;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试使用线程池与不使用线程池的差别
 */
public class ThreadTest {
    public static void main(String[] args) {
        double startTime = System.currentTimeMillis();
        ExecutorService es = Executors.newFixedThreadPool(2);
        for(int i=0;i<10000;i++){
            int finalI = i;
            es.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(finalI);
                }
            });
        }
        double endTime1 = System.currentTimeMillis();
        for(int i=0;i<10000;i++){
            int finalI = i;
            new Thread(){
                @Override
                public void run() {
                    System.out.println(finalI);
                }
            }.start();
        }
        double endTime2 = System.currentTimeMillis();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("使用线程池运行："+(endTime1-startTime));
        System.out.println("不是用线程池运行："+(endTime2-endTime1));


        es.shutdown();
    }
}
