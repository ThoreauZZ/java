package com.henry.chain;

/**
 * 2018/5/17 下午9:51.
 *
 * @author zhaozhou
 */
public class Handler1 extends Handler{

    @Override
    void doHand(String str) {
        if ("xx".equals(str)) {
            System.out.println("handler1 处理...");
        }else {
            System.out.println("给下一个Handler处理...");
            next.doHand(str);
        }
    }
}
