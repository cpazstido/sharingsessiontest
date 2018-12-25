package com.cf.sessiontest.thread;

import java.util.concurrent.Semaphore;

/**
 * 使用信号量实现打印
 */
public class printABSemaphore {
    static int num = 1;
    static int index = 0;
    static Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程a启动");
                try {
                    while (num < 100) {
                        semaphore.acquire();
//                        System.out.println("线程a加锁成功");
                        if (index % 2 == 0) {
                            System.out.println("线程a:=================" + (num++));
                            index++;
                        }
                        semaphore.release();
                    }
                } catch (Exception e) {

                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程b启动");
                try {
                    while (num < 100) {
                        semaphore.acquire();
//                        System.out.println("线程b加锁成功************");
                        if (index % 2 == 1) {
                            System.out.println("线程b:" + (num++));
                            index++;
                        }
                        semaphore.release();
                    }
                } catch (Exception e) {

                }

            }
        }).start();
    }
}
