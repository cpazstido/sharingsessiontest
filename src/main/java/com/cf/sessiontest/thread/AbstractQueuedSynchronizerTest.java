package com.cf.sessiontest.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AbstractQueuedSynchronizerTest {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Runnable runnable0 = new ReentrantLockThread(lock,3);
        Thread thread0 = new Thread(runnable0);
        thread0.setName("线程0");

        Runnable runnable1 = new ReentrantLockThread(lock,2);
        Thread thread1 = new Thread(runnable1);
        thread1.setName("线程1");

        Runnable runnable2 = new ReentrantLockThread(lock,1);
        Thread thread2 = new Thread(runnable2);
        thread2.setName("线程2");

        thread0.start();
        thread1.start();
        thread2.start();

        for (; ; ) ;
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
                    Thread.sleep(sTime*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                System.out.println(Thread.currentThread().getName()+"执行完毕！");
                lock.unlock();
            }
        }

    }
}
