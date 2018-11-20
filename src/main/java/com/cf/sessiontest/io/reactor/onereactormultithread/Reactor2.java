package com.cf.sessiontest.io.reactor.onereactormultithread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单Reactor多线程模型
 *
 * 当获取到IO的读写事件之后，
 * 交由线程池来处理，
 * 这样可以减小主reactor的性能开销，
 * 从而更专注的做事件分发工作了，从而提升整个应用的吞吐。
 */
public class Reactor2 {
    private static ExecutorService pool = Executors.newFixedThreadPool(100);
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(1234));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while(true) {
            if(selector.selectNow() < 0){
                continue;
            }
            Set<SelectionKey> sets = selector.selectedKeys();
            Iterator<SelectionKey> keys = sets.iterator();
            while(keys.hasNext()) {
                SelectionKey key = keys.next();
                keys.remove();
                try {
                    if(key.isAcceptable()) {
                        ServerSocketChannel Serverchannel = (ServerSocketChannel) key.channel();
                        SocketChannel channel = Serverchannel.accept();
                        channel.configureBlocking(false);
                        System.out.println("accept from "+channel.socket().getInetAddress().toString());
                        channel.register(selector, SelectionKey.OP_READ);
                    }else if(key.isValid()&&key.isReadable()) {
                        pool.submit(new Processor(key));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
