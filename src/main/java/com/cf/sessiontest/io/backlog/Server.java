package com.cf.sessiontest.io.backlog;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception{

        // port:8888,backlog:5
        ServerSocket server = new ServerSocket(8888, 3);

        int acceptCount = 0;
        while(true)
        {
            Socket client = server.accept();
            acceptCount++;
            System.out.println("new connection has connected, num=" + acceptCount);
            Thread.sleep(2 * 1000);
        }

    }
}
