package com.thoreau.netty.blockingqueue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 2018/5/14 下午3:14.
 *
 * @author zhaozhou
 * @date 2018/05/14
 */
public class NettyClientTest {
    private static AtomicLong atomicLong = new AtomicLong(0);

    public static long getRequestId() {
        return System.nanoTime() + atomicLong.getAndAdd(1);
    }

    @Before
    public void setup() throws Exception {
        NettyServer.start();
    }

    @Test
    public void testMultiRequestClient() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            int number = i;
            executorService.submit(() -> {
                ClientHandler handler = null;
                try {
                    handler = (ClientHandler)NettyClient.getHandler();
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Request request = new Request();
                request.setRequestId(getRequestId());
                request.setData("request data" + number);
                Response result = handler.invoke(request);
                System.out.println("blockingqueue " + number + " received: " + result.getData());
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        NettyClient.shutDown();
    }

    @After
    public void stop() {
        NettyServer.shutdown();
    }
}