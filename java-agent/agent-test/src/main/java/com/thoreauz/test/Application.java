package com.thoreauz.test;

/**
 * 2019/1/8 11:22 PM.
 *
 * @author zhaozhou
 */
public class Application {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("hello world!");
        add(1, 2);
    }

    private static int add(int a, int b) throws InterruptedException {
        Thread.sleep(300);
        return a + b;
    }
}
