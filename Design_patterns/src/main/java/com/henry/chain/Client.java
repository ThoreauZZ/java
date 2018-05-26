package com.henry.chain;

/**
 * 2018/5/17 下午10:08.
 *
 * @author zhaozhou
 */
public class Client {
    public static void main(String[] args) {
        Handler handler1 = new Handler1();
        Handler handler2 = new Handler2();
        handler1.setNext(handler2);
        handler1.doHand("yy");
    }
}
