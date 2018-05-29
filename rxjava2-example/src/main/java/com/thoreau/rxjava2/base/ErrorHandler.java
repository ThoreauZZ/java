package com.thoreau.rxjava2.base;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import org.junit.Test;

/**
 * 2018/5/30 上午1:15.
 *
 * @author zhaozhou
 */
public class ErrorHandler {
    @Test
    public void testError() {
        Flowable.create(new FlowableOnSubscribe<Object>() {
            @Override
            public void subscribe(FlowableEmitter<Object> emitter) throws Exception {
                for (int i = 0; i < 5; i++) {
                    if (i == 0) {
                        emitter.onNext("A");
                    } else {
                        emitter.onError(new RuntimeException());
//                        throw new RuntimeException();
                    }
                }
            }
        }, BackpressureStrategy.BUFFER)
//                .onErrorResumeNext(Flowable.just("E"))
                .subscribe(System.out::println);

    }
}
