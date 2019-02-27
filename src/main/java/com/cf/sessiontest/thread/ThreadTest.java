package com.cf.sessiontest.thread;

public class ThreadTest {
    public static void main(String[] args) throws Exception{
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("ss");
            }
        });

        thread.start();
        Thread.sleep(2000);
        thread.start();

    }
}
