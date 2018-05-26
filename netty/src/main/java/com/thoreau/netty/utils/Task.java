package com.thoreau.netty.utils;

import io.netty.util.HashedWheelTimer;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * 2018/5/22 上午9:25.
 *
 * @author zhaozhou
 * @date 2018/05/22
 */
public class Task {
    public static void main(String[] args) throws InterruptedException {
        HashedWheelTimer timer = new HashedWheelTimer(1, TimeUnit.SECONDS, 60);
        timer.newTimeout(timeout -> System.out.println("5 second"), 5, TimeUnit.SECONDS);

        Timer jdk = new Timer(true);
        jdk.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("go");
            }
        },200);
        Thread.sleep(20000);
    }
}
