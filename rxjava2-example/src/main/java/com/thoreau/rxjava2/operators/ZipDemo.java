package com.thoreau.rxjava2.operators;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Zip通过一个函数将多个Observable发送的事件结合到一起，然后发送这些组合到一起的事件. 它按照严格的顺序应用这个函数。它只发射与发射数据项最少的那个Observable一样多的数据。
 *
 * @author zhaozhou
 * @date 2018/05/30
 */
@Slf4j
public class ZipDemo {
    @Test
    public void test() {
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                log.info("emit 1");
                emitter.onNext(1);
                log.info("emit 2");
                emitter.onNext(2);
                log.info("emit 3");
                emitter.onNext(3);
                log.info("emit 4");
                emitter.onNext(4);
                log.info("emit complete");
                emitter.onComplete();
            }
        });

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                log.info("emit A");
                emitter.onNext("A");
                log.info("emit B");
                emitter.onNext("B");
                log.info("emit C");
                emitter.onNext("C");
                log.info("emit complate");
                emitter.onComplete();
            }
        });

        // 因为用zip，让observable1发送数据完成后（缓存起来），observable2再发送一个，订阅者订阅一个的执行。如果不想这样，需要切换线程
        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                log.info("onSubscribe");
            }
            @Override
            public void onNext(String value) {
                log.info("onNext{}",value);
            }

            @Override
            public void onError(Throwable e) {
                log.info("onError{}",e);
            }

            @Override
            public void onComplete() {
                log.info("onComplete");
            }
        });

    }

}
