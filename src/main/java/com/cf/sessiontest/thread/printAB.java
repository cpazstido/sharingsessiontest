package com.cf.sessiontest.thread;

/**
 * 两个线程交替打印数字
 * 这种方式会造成线程空转
 * 使用公平锁也会造成空转
 */
public class printAB {
    static Object object = new Object();
    static int num = 1;
    static int index = 1;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程a启动");
                while (num < 100) {
                    synchronized (object) {
                        System.out.println("线程a加锁成功");
                        if (index % 2 == 0) {
                            System.out.println("线程a:=================" + (num++));
                            index++;
                        }

                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程b启动");
                while (num < 100) {
                    synchronized (object) {
                        System.out.println("线程b加锁成功************");
                        if (index % 2 == 1) {
                            System.out.println("线程b:" + (num++));
                            index++;
                        }
                    }
                }
            }
        }).start();
    }
}
