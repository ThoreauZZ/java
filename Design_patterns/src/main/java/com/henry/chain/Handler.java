package com.henry.chain;

/**
 * 2018/5/17 下午9:47.
 *
 * @author zhaozhou
 */
public abstract class Handler {
    protected Handler next;
    abstract void doHand(String str);

    protected void setNext(Handler next) {
        this.next = next;
    }
}
