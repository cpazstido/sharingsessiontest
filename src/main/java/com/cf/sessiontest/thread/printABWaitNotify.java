package com.cf.sessiontest.thread;

public class printABWaitNotify {
    static int num = 1;
    static Object object = new Object();

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程a启动");
                try {
                    while (num < 100) {
                        System.out.println("线程1 =========" + num);
                        synchronized (object) {
                            object.notify();
                            System.out.println("线程1：" + num++);
                            if (num < 100) {
                                object.wait();
                            }

                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程b启动");
                try {
                    while (num < 100) {
                        System.out.println("线程2 =========" + num);
                        synchronized (object) {
                            object.notify();
                            System.out.println("线程2：" + num++);
                            if (num < 100) {
                                object.wait();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        t1.start();
        t2.start();
    }
}
