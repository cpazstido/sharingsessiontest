package com.cf.sessiontest.thread;

import javax.tools.ToolProvider;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        ConcurrentHashMap map = new ConcurrentHashMap();
        map.put("","");
        map.get("");
        Thread.holdsLock("");

        Thread thread1 = new Thread();
        thread1.notify();




        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.tryLock();
//        lock.lockInterruptibly();
        try {
            Thread.sleep(100);
            Thread thread = new Thread();
            thread.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
