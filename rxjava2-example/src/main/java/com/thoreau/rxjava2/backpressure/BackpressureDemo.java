package com.thoreau.rxjava2.backpressure;

import io.reactivex.*;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.reactivestreams.Subscription;

/**
 * 2018/5/30 下午8:42.
 *
 * @author zhaozhou
 * @date 2018/05/30
 */
@Slf4j
public class BackpressureDemo {
    @Test
    public void badEmit() throws InterruptedException {
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; ; i++) {
                    emitter.onNext(i);
                }
            }
        }).subscribeOn(Schedulers.io());

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("A");
            }
        }).subscribeOn(Schedulers.io());

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).observeOn(Schedulers.computation()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
            }
        });
        Thread.sleep(5000);
    }
    @Test
    public void testBP_ERROR() throws InterruptedException {
        //ERROR 如果放入Flowable的异步缓存池中的数据超限了，则会抛出MissingBackpressureException异常。
        Flowable.create(flowableEmitter -> {
            for (int i = 0; i < 129; i++) {
                log.info("emit " + i);
                flowableEmitter.onNext(i);
            }
        }, BackpressureStrategy.ERROR)
                .observeOn(Schedulers.io())
                .subscribe(new FlowableSubscriber<Object>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        subscription.request(Long.MAX_VALUE);
                    }
                    @Override
                    public void onNext(Object o) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        log.info("接收数据{}", o);
                    }
                    @Override
                    public void onError(Throwable throwable) {
                        log.error("err", throwable);
                    }
                    @Override
                    public void onComplete() {
                    }
                });
        Thread.sleep(1000);
    }
    @Test
    public void testBP_DROP() throws InterruptedException {
        //DROP Flowable的异步缓存池满了，会丢掉将要放入缓存池中的数据。
        //注意：如果被消费每累积到95条清理一次
        Flowable.create(flowableEmitter -> {
            for (int i = 0; i < 500; i++) {
                Thread.sleep(10);
                log.info("emit " + i);
                flowableEmitter.onNext(i);
            }
        }, BackpressureStrategy.DROP)
                .observeOn(Schedulers.io())
                .subscribe(new FlowableSubscriber<Object>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        subscription.request(Long.MAX_VALUE);
                    }
                    @Override
                    public void onNext(Object o) {
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        log.info("接收数据{}", o);
                    }
                    @Override
                    public void onError(Throwable throwable) {
                        log.error("err", throwable);
                    }
                    @Override
                    public void onComplete() {
                    }
                });
        Thread.sleep(3000);
    }
    @Test
    public void testBP_ERROR2() throws InterruptedException {
        // 95 个数据被消费，才会被清理
        Flowable.create(flowableEmitter -> {
            for (int i = 0; i < 129; i++) {
                Thread.sleep(20);
                log.info("emit " + i);
                flowableEmitter.onNext(i);
            }
        }, BackpressureStrategy.ERROR)
                .observeOn(Schedulers.io())
                .subscribe(new FlowableSubscriber<Object>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        subscription.request(96);
                    }
                    @Override
                    public void onNext(Object o) {
                        log.info("接收数据{}", o);
                    }
                    @Override
                    public void onError(Throwable throwable) {
                        log.error("err", throwable);
                    }
                    @Override
                    public void onComplete() {
                    }
                });
        Thread.sleep(1000);
    }
    @Test
    public void testBP_perfect() throws InterruptedException {
        Flowable.create(flowableEmitter -> {
            int i = 0;
            while (true) {
                if (flowableEmitter.requested() == 0) {
                    continue;
                }
                i++;
                log.info("emit {}，request {}" , i,flowableEmitter.requested());
                flowableEmitter.onNext(i);
            }
        }, BackpressureStrategy.ERROR)
                .observeOn(Schedulers.io())
                .subscribe(new FlowableSubscriber<Object>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        subscription.request(Long.MAX_VALUE);
                    }
                    @Override
                    public void onNext(Object o) {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        log.info("接收数据{}", o);
                    }
                    @Override
                    public void onError(Throwable throwable) {
                        log.error("err", throwable);
                    }
                    @Override
                    public void onComplete() {
                    }
                });
        Thread.sleep(1000);
    }
}
