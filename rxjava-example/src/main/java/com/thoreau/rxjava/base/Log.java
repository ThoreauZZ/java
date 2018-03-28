package com.thoreau.rxjava.base;

/**
 * 2018/3/27 12:21.
 *
 * @author zhaozhou
 */
public class Log {
    public static void info(Object msg) {
        System.out.println(Thread.currentThread().getName() + ": " + msg);
    }
}
