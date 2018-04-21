package com.thoreauz.bootlearn.testng.timeout;

import org.testng.annotations.Test;

/**
 * 2018/4/21 上午12:58.
 *
 * @author zhaozhou
 */
public class TimeoutSuite {
    @Test(timeOut = 2000)
    public void timeTestOne() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Time test method one");
    }

    @Test(timeOut = 500)
    public void timeTestTwo() throws InterruptedException {
        Thread.sleep(400);
        System.out.println("Time test method two");
    }
}
