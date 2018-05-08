package com.thoreauz.bootlearn.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 2018/5/8 下午2:43. //TODO 需要完善
 *
 * @author thoreau
 * @date 2018/05/08
 */
public class ReactiveEchoServer implements Runnable {
    private static ExecutorService workerPool;
    private final Selector selector;
    private final ServerSocketChannel socketChannel;

    ReactiveEchoServer(int port) throws IOException {
        // 1. 创建selector
        selector = Selector.open();


        // 2. 创建channel
        socketChannel = ServerSocketChannel.open();
        socketChannel.socket().bind(new InetSocketAddress(port));

        //与Selector一起使用时，Channel必须处于非阻塞模式下
        socketChannel.configureBlocking(false);

        // 3. 向Selector注册通道,监听ACCEPT事件
        SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 4. 添加附件
        selectionKey.attach(new Acceptor());
    }

    public static void main(String[] args) {
        workerPool = Executors.newFixedThreadPool(8);
        try {
            // a single thread blocking on selector for events
            Executors.newSingleThreadExecutor().submit(new ReactiveEchoServer(9090));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 阻塞直到有通道在注册的事件上就绪了
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for (SelectionKey selectionKey : selectionKeys) {
                    // Reactor负责dispatch收到的事件
                    dispatch(selectionKey);
                }
                //selectionKey 这个对象代表了注册到该Selector的通道
                selectionKeys.clear();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    void dispatch(SelectionKey selectionKey) {
        // 调用之前注册的callback对象
        Runnable r = (Runnable)(selectionKey.attachment());
        if (r != null) {
            // Acceptor.run()
            r.run();
        }
    }

    class Acceptor implements Runnable {
        @Override
        public void run() {
            try {
                SocketChannel socketChannel = ReactiveEchoServer.this.socketChannel.accept();
                if (socketChannel != null) {
                    new Handler(selector, socketChannel);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static ExecutorService getWorkerPool() {
        return workerPool;
    }

    class Handler implements Runnable {
        final SocketChannel socketChannel;
        final SelectionKey selectionKey;

        private static final int READ_BUF_SIZE = 1024;
        private static final int WRITE_BUF_SIZE = 1024;
        private ByteBuffer byteBuffer = ByteBuffer.allocate(READ_BUF_SIZE);
        private ByteBuffer writeBuf = ByteBuffer.allocate(WRITE_BUF_SIZE);

        Handler(Selector s, SocketChannel socketChannel) throws IOException {
            this.socketChannel = socketChannel;
            this.socketChannel.configureBlocking(false);
            // Register _socketChannel with _selector listening on OP_READ events.
            selectionKey = this.socketChannel.register(s, 0);

            // Callback: Handler, selected when the connection is established and ready for READ
            selectionKey.attach(this);
            selectionKey.interestOps(SelectionKey.OP_READ);

            // let blocking select() return
            s.wakeup();
        }
        synchronized void process() {
            byteBuffer.flip();
            byte[] bytes = new byte[byteBuffer.remaining()];
            byteBuffer.get(bytes, 0, bytes.length);
            System.out.print("process(): " + new String(bytes, Charset.forName("UTF-8")));
            writeBuf = ByteBuffer.wrap(bytes);

            selectionKey.interestOps(SelectionKey.OP_WRITE);
            selectionKey.selector().wakeup();
        }

        @Override
        public void run() {
            if (selectionKey.isReadable()) {
                read();
            } else if (selectionKey.isWritable()) {
                write();
            }

        }

        synchronized void read() {
            try {

                int numBytes = socketChannel.read(byteBuffer);

                System.out.println("read(): #bytes read into 'byteBuffer' buffer = " + numBytes);

                if (numBytes == -1) {
                    socketChannel.close();
                    System.out.println("read(): client connection might have been dropped!");
                } else {
                    ReactiveEchoServer.getWorkerPool().execute(() -> process());
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        void write() {
            int numBytes = 0;
            try {
                numBytes = socketChannel.write(writeBuf);
                System.out.println("write(): #bytes wirte from 'writeBuf' buffer = " + numBytes);
                if (numBytes > 0) {
                    byteBuffer.clear();
                    writeBuf.clear();
                    selectionKey.interestOps(SelectionKey.OP_READ);
                    selectionKey.selector().wakeup();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
