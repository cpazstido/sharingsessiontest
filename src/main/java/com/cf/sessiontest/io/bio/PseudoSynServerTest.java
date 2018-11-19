package com.cf.sessiontest.io.bio;

import java.io.IOException;

/**
 * 伪异步服务端测试
 */
public class PseudoSynServerTest {
    public static void main(String[] args) {
        //启动线程，运行服务器
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerBetter.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
