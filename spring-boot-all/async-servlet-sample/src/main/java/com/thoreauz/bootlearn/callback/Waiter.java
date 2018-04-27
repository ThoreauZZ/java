package com.thoreauz.bootlearn.callback;

/**
 * 2018/5/8 上午11:18.
 *
 * @author zhaozhou
 * @date 2018/05/08
 */
public class Waiter implements CallBack {
    private Chef chef;
    private CallBack callBack;

    @Override
    public void call() {
        System.out.println("服务员上菜....");
    }
    public static Waiter BindWaiter (Chef chef){
        Waiter waiter = new Waiter();
        waiter.chef = chef;
        return waiter;
    }

    public Waiter addCallBack(CallBack callBack){
        this.callBack = callBack;
        return this;
    }
    public void go() {
        System.out.println("通知厨司做饭...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    chef.doCooking(callBack);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        System.out.println("继续做其他事情...");
    }
}
