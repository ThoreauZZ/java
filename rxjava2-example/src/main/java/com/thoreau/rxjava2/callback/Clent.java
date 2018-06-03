package com.thoreau.rxjava2.callback;

/**
 * 2018/6/1 下午10:32.
 *
 * @author zhaozhou
 */
public class Clent {
    public static void main(String[] args) {
        Caller caller = new Caller(new CallBack() {
            @Override
            public void call() {
                System.out.println("回调方法被执行...");
            }
        });
        caller.doSomething();
    }
}
