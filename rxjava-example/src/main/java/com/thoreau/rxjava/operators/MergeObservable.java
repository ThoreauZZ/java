package com.thoreau.rxjava.operators;

import org.junit.Test;
import rx.Observable;

/**
 * 2018/3/29 11:36.
 *
 * @author zhaozhou
 */
public class MergeObservable {
    @Test
    public void mergeTest() {
        Observable.merge(Observable.just("hello", "java")
                , Observable.unsafeCreate((Observable.OnSubscribe<Object>) onSubscriber -> {
                    onSubscriber.onNext(10000);
                    onSubscriber.onError(new RuntimeException());
                }), Observable.just("1", "2"))
                  .subscribe(System.out::println, Throwable::printStackTrace, () -> System.out.println("complete"));
    }

    @Test
    public void mergeDelayErrorTest() {
        //postpone any errors until all of the other streams have finished
        Observable.mergeDelayError(Observable.just("hello", "java")
                , Observable.unsafeCreate((Observable.OnSubscribe<Object>) onSubscriber -> {
                    onSubscriber.onNext(10000);
                    onSubscriber.onError(new RuntimeException());
                }), Observable.just("1", "2"))
                  .subscribe(System.out::println,
                          (Throwable t) -> System.out.println("err--> " + t.getMessage()),
                          () -> System.out.println("complete"));
    }
}
