package com.cf.sessiontest.io.reactor.onereactoronethread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 单Reactor单线程模型(接受和处理使用同一个线程，一旦处理时间太长，则整个系统吞吐量降低)
 *
 * 尽管一个线程可同时监控多个请求（Channel），
 * 但是所有读/写请求以及对新连接请求的处理都在同一个线程中处理，
 * 无法充分利用多CPU的优势,同时读/写操作也会阻塞对新连接请求的处理。
 *
 */
public class Reactor1 {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(1234));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (selector.select() > 0) {
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    ServerSocketChannel acceptServerSocketChannel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = acceptServerSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    System.out.println("accept from "+socketChannel.socket().getInetAddress().toString());
                    //  LOGGER.info("Accept request from {}", socketChannel.getRemoteAddress());
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable() && key.isValid()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int count = socketChannel.read(buffer);
                    if (count <= 0) {
                        socketChannel.close();
                        key.cancel();
                        System.out.println("Received invalide data, close the connection");
                        //LOGGER.info("Received invalide data, close the connection");
                        continue;
                    }
                    System.out.println("Received message"+new String(buffer.array()));
                    socketChannel.write(ByteBuffer.wrap(new String("return").getBytes()));
                    //LOGGER.info("Received message {}", new String(buffer.array()));
                }
                keys.remove(key);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
