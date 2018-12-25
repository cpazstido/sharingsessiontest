package com.cf.sessiontest.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 交替打印1a2b3c4d
 */
public class printNumABC {
    static Object object = new Object();
    static CountDownLatch latch = new CountDownLatch(2);
    static int index = 0;
    static int num_max = 26;
    static Lock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程1启动");
                for (int i = 0; i < num_max; i++) {
                    while (true) {
                        lock.lock();
                        System.out.println("a "+index);
                        if (index % 2 == 0) {
                            System.out.println("线程1:" + (i + 1));
                            index++;
                            lock.unlock();
                            break;
                        }
                        lock.unlock();
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程2启动");
                for (int i = 0; i < num_max; i++) {
                    while (true) {
                        lock.lock();
                        System.out.println("b "+index);
                        if (index % 2 == 1) {
                            System.out.println("线程2:" + (char) (i + 97));
                            lock.unlock();
                            index++;
                            break;
                        }
                        lock.unlock();

                    }
                }
            }
        });

        t1.start();

        t2.start();
    }
}
