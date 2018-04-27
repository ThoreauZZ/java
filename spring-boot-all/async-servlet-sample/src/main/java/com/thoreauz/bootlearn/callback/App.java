package com.thoreauz.bootlearn.callback;

/**
 * 2018/5/8 上午11:25.
 *
 * @author zhaozhou
 * @date 2018/05/08
 */
public class App {
    public static void main(String[] args) {
        Waiter.BindWaiter(new Chef())
              .addCallBack(() -> {
            System.out.println("通知我");
        }).go();
    }
}
