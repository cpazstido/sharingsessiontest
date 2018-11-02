package com.cf.sessiontest.test;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
    public static void main(String[] args) {
        Timer timer = new Timer();


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("hahha");
            }
        },1000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int i = 1/0;
            }
        },2000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("hahha");
            }
        },3000);
    }
}
