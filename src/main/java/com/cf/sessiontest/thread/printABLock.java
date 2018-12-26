package com.cf.sessiontest.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用锁实现交替打印
 */
public class printABLock {
    static int num = 1;
    static int index = 1;
    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程a启动");
                while (num <= 100) {
                    lock.lock();
                    if(num>100){
                        lock.unlock();
                        break;
                    }
                    if (index % 2 == 1) {
                        System.out.println("线程1:" + num);
                        num++;
                        index++;
                        lock.unlock();
                        continue;
                    }
                    lock.unlock();
                }
//                System.out.println("线程1结束："+num);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程b启动");
                while (num <= 100) {
                    lock.lock();
                    if(num>100){
                        lock.unlock();
                        break;
                    }
                    if (index % 2 == 0) {
                        System.out.println("线程2:" + num);
                        num++;
                        index++;
                        lock.unlock();
                        continue;
                    }
                    lock.unlock();
                }
//                System.out.println("线程2结束："+num);
            }
        });

        t1.start();

        t2.start();
    }
}
