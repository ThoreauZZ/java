package com.henry.chain;

/**
 * 2018/5/17 下午10:07.
 *
 * @author zhaozhou
 */
public class Handler2 extends Handler {
    @Override
    void doHand(String str) {
        System.out.println("handler2 处理...");
        if (next != null) {
            next.doHand(str);
        }
    }
}
