package com.cf.sessiontest.io.bio;

import java.util.Random;

/**
 * 伪异步客户端测试
 */
public class PseudoSynClientTest {
    public static void main(String[] args) {
        //启动线程，运行客户端
        char operators[] = {'+', '-', '*', '/'};
        Random random = new Random(System.currentTimeMillis());
        while (true){
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    while (true) {
                        //随机产生算术表达式
                        String expression = random.nextInt(10) + "" + operators[random.nextInt(4)] + (random.nextInt(10) + 1);
                        BioClient.send(expression);
//                    try {
//                        Thread.currentThread().sleep(random.nextInt(1000));
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    }
                }
            }).start();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
