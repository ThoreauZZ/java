package com.thoreauz.bootlearn.callback;

/**
 * 2018/5/8 上午11:20.
 *
 * @author zhaozhou
 * @date 2018/05/08
 */
public class Chef {
    public void doCooking(CallBack callBack) throws InterruptedException {
        System.out.println("厨司正在做饭..");
        Thread.sleep(500);
        System.out.println("饭做好了...");
        if (callBack != null) {
            callBack.call();

        }
    }
}
