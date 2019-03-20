package com.cf.sessiontest.thread;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockSynchronizeTest {
    public static void main(String[] args) throws Exception{
        //使用synchronize等待锁的线程无法被中断,调用interrupt()后线程状态依然是Runnable状态
//        synchronizeTest();


        //使用lockInterruptibly()等待锁的线程可以被中断
        lockTest();
    }

    public static void lockTest() throws Exception{
        Lock lock = new ReentrantLock();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName()+" begin read");
                    lock.lockInterruptibly();
                    System.in.read();
//                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName()+" exit read");
                } catch (Throwable e) {
                    System.out.println(Thread.currentThread().getName()+" interrupted");
                }finally {
                    if(Thread.interrupted())
                    lock.unlock();
                }
            }
        });

        thread.start();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName()+" begin read");
                    lock.lockInterruptibly();
                    System.in.read();
//                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName()+" exit read");
                } catch (Throwable e) {
                    System.out.println(Thread.currentThread().getName()+" interrupted");
                }finally {
                    if(Thread.interrupted())
                    lock.unlock();
                }
            }
        });

        Thread.sleep(300);
        thread1.start();
        Thread.sleep(300);
        System.out.println("开始中断");
        thread1.interrupt();
    }

    /**
     * synchronized无法被中断
     * @throws Exception
     */
    public static void synchronizeTest() throws Exception{
        Object o = new Object();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o){
                    try {
                        System.out.println(Thread.currentThread().getName() + "begin read");
                        System.in.read();
//                        Thread.sleep(3000);
                        System.out.println(Thread.currentThread().getName() + "exit read");
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o){
                    try {
                        System.out.println(Thread.currentThread().getName() + "begin read");
                        System.in.read();
//                        Thread.sleep(3000);
                        System.out.println(Thread.currentThread().getName() + "exit read");
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread.sleep(300);
        thread1.start();
        Thread.sleep(1000);
        thread1.interrupt();
    }
}
