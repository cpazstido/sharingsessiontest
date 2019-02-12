package com.cf.sessiontest.thread;

import java.util.concurrent.locks.LockSupport;

public class LockTest {
    public static void main(String[] args) {
        LockSupport.park();
        System.out.println("");
    }
}
