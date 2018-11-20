package com.cf.sessiontest.io.reactor.mutilreactormultithread;


import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FollowerReactor {
    private Selector selector;
    private static ExecutorService service = Executors.newFixedThreadPool(
            2*Runtime.getRuntime().availableProcessors());
    public void register(SocketChannel socketChannel) throws ClosedChannelException {
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    public void wakeUp() {
    }

    public FollowerReactor() throws IOException {
        selector = Selector.open();
        select();

    }
    public void wakeup() {
        this.selector.wakeup();
    }
    public void select() throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (selector.select(10) < 0) {
                            continue;
                        }
                        Set<SelectionKey> sets = selector.selectedKeys();
                        Iterator<SelectionKey> keys = sets.iterator();
                        while (keys.hasNext()) {
                            SelectionKey key = keys.next();
                            keys.remove();
                            SocketChannel channel = null;
                            try {
                                if (key.isValid()&&key.isAcceptable()) {
                                    ServerSocketChannel Serverchannel = (ServerSocketChannel) key.channel();
                                    channel = Serverchannel.accept();
                                    channel.configureBlocking(false);
                                    System.out.println("accept from " + channel.socket().getInetAddress().toString());
                                    channel.register(selector, SelectionKey.OP_READ);
                                } else if (key.isValid() && key.isReadable()) {
                                    service.submit(new Processor(key));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
