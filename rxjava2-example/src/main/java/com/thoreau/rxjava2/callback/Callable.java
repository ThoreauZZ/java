package com.thoreau.rxjava2.callback;


/**
 * 2018/6/3 下午6:58.
 *
 * @author zhaozhou
 */
interface Callback<T> {
    void onCompleted();
    void onError(Throwable t);
    void onNext(T var1);
}
public class Callable {
    final OnCall onCall;

    public Callable(OnCall onCall) {
        this.onCall = onCall;
    }

    public void invoke(Callback callback) {
        onCall.call(callback);
    }

    public interface OnCall<T> {
        void call(Callback callback);
    }

    public static void main(String[] args) {
        new Callable(observer -> {
            observer.onNext(1);
            observer.onCompleted();
        }).invoke(new Callback() {
            @Override
            public void onCompleted() { System.out.println("complete ... "); }
            @Override
            public void onError(Throwable t) { }
            @Override
            public void onNext(Object var1) { System.out.println(var1); }
        });
    }
}

