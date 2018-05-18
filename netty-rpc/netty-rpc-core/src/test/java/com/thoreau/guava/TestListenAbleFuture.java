package com.thoreau.guava;

import com.google.common.util.concurrent.*;
import org.junit.Test;

import javax.annotation.Nullable;
import java.util.concurrent.Executors;

/**
 * 2018/5/18 下午8:46.
 *
 * @author zhaozhou
 */
public class TestListenAbleFuture {
    @Test
    public void test() {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        ListenableFuture<String> future = service.submit(() -> {
            System.out.println("do something in runnable");
            return "OK";
        });
        Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(@Nullable String result) {
                System.out.println("success:" + result);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        },service);
    }
}
