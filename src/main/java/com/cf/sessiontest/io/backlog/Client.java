package com.cf.sessiontest.io.backlog;

import java.net.Socket;

public class Client {
    private static Socket[] clients = new Socket[30];
    public static void main(String[] args) throws Exception {
        for (int i = 1; i <= 30; i++) {
            clients[i-1] = new Socket("127.0.0.1", 8888);
            System.out.println("client connection:" + i);
        }
    }
}
