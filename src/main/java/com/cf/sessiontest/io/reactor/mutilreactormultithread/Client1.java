package com.cf.sessiontest.io.reactor.mutilreactormultithread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Client1 {
    public static void main(String[] args) throws IOException, InterruptedException {
        int i = 0;
        while(i<1){
            i++;
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        SocketChannel socketChannel;
                        socketChannel = SocketChannel.open();
                        //socketChannel.configureBlocking(false);
                        socketChannel.connect(new InetSocketAddress("localhost", 1234));
                        Date now = new Date();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
                        String str = String.valueOf(finalI);
                        byte[] requst = str.getBytes();
                        ByteBuffer buffer = ByteBuffer.allocate(requst.length);
                        buffer.put(requst);
                        buffer.flip();
                        try {
                            while (buffer.hasRemaining()) {
                                socketChannel.write(buffer);
                            }
                        }catch (IOException e) {
                            e.printStackTrace();
                        }

                        //创建读取数据缓冲器
                        buffer = ByteBuffer.allocate(100);
                        int read = socketChannel.read(buffer);
                        byte[] data = buffer.array();
                        String message = new String(data);

                        System.out.println("receive message from client, size:" + buffer.position() + " msg: " + message);

                        socketChannel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            Random random = new Random();
            int sleepTime = random.nextInt(5)*100;
//            System.out.println("休息："+sleepTime);
            Thread.sleep(sleepTime);
        }

    }
}
