package com.cf.sessiontest.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class AbstractQueuedSynchronizerTest {
    public static void main(String[] args) throws Exception {
        Lock lock = new ReentrantLock();

        Runnable runnable0 = new ReentrantLockThread(lock,300000);
        Thread thread0 = new Thread(runnable0);
        thread0.setName("线程0");

        Runnable runnable1 = new ReentrantLockThread(lock,200000);
        Thread thread1 = new Thread(runnable1);
        thread1.setName("线程1");

        Runnable runnable2 = new ReentrantLockThread(lock,100000);
        Thread thread2 = new Thread(runnable2);
        thread2.setName("线程2");

        Runnable runnable3 = new ReentrantLockThread(lock,100000);
        Thread thread3 = new Thread(runnable3);
        thread3.setName("线程3");

        thread0.start();
        Thread.sleep(300);
        thread1.start();
        Thread.sleep(300);
        thread2.start();
        Thread.sleep(300);
        thread3.start();

        Thread.sleep(3000);
        System.out.println("进入中断");
        thread0.interrupt();
//        thread2.stop();
//        for (; ; ) ;
    }

    private static class ReentrantLockThread implements Runnable {
        private Lock lock;
        private int sTime;

        public ReentrantLockThread(Lock lock,int time) {
            this.lock = lock;
            this.sTime = time;
        }

        @Override
        public void run() {
            try {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(sTime*1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } finally {
                System.out.println(Thread.currentThread().getName()+"执行完毕！");
                lock.unlock();
            }
        }
    }
}
