package com.thoreauz.bootlearn.testng;

import org.testng.annotations.Test;

/**
 * 2018/4/21 上午12:40.
 *
 * @author zhaozhou
 */
public class DisableTestDemo {
    @Test(enabled = true)
    public void testMethodOne() {
        System.out.println("Test method one.");
    }

    @Test(enabled = false)
    public void testMethodTwo() {
        System.out.println("Test method two.");
    }

    @Test
    public void testMethodThree() {
        System.out.println("Test method three.");
    }
}
