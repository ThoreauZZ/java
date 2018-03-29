package com.thoreau.rxjava.operators;

import rx.Observable;
import rx.Subscriber;

/**
 * 2018/3/29 12:16.
 *
 * @author zhaozhou
 */
public class CustomOperator {
    public static void main(String[] args) {
        Observable.range(1, 9)
                  .lift(toStringOfOdd())
                  .subscribe(System.out::println);
    }

    static <T> Observable.Operator<String, T> toStringOfOdd() {
        return new Observable.Operator<String, T>() {
            private boolean odd = true;

            @Override
            public Subscriber<? super T> call(Subscriber<? super String> child) {
                return new Subscriber<T>(child) {
                    @Override
                    public void onCompleted() {
                        child.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        child.onError(e);
                    }

                    @Override
                    public void onNext(T t) {
                        if (odd) {
                            child.onNext(t.toString());
                        } else {
                            request(1);
                        }
                        odd = !odd;
                    }
                };
            }
        };
    }
}
