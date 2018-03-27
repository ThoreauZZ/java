package com.thoreau.rxjava.base;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * 2018/3/27 12:12.
 *
 * @author zhaozhou
 */
@Slf4j
public class ObserverExample {
    @Test
    public void createTest() {
        Observable<String> observable = Observable.just("Hello", "RxJava");
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.info("event completed");
            }

            @Override
            public void onError(Throwable e) {
                Log.info("error " + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Log.info("next -> " + s);
            }
        };
        observable.subscribe(observer);

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.info("event completed");
            }

            @Override
            public void onError(Throwable e) {
                Log.info("error " + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Log.info("next -> " + s);
            }
        };
        observable.subscribe(subscriber);

        System.out.println(subscriber.isUnsubscribed());
        subscriber.unsubscribe();
        System.out.println(subscriber.isUnsubscribed());
    }

}
