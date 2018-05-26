package com.henry.chain;

/**
 * 2018/5/17 下午10:48.
 *
 * @author zhaozhou
 */
public interface Intercepter {
    void chian(Chain chain);

    interface Chain {

    }
}
