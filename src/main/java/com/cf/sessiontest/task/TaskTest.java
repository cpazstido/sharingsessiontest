package com.cf.sessiontest.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskTest {

    @Scheduled(cron = "0 * *  * * ? ")
    public void test(){
        System.out.println("test");
    }
}
