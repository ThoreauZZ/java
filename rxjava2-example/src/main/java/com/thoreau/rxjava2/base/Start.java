package com.thoreau.rxjava2.base;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.reactivestreams.Subscription;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 2018/5/29 下午10:14.
 *
 * @author zhaozhou
 */
@Slf4j
public class Start {
    @Test
    public void base() {
        //被观察者
        Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                log.info("被观察者发送数据：test1");
                e.onNext("test1");
                log.info("被观察者发送数据：test2");
                e.onNext("test2");
                log.info("被观察者发送数据：test3");
                e.onNext("test3");
                log.info("被观察者发送数据：test4");
                e.onNext("test4");

                // 订阅者收到此事件后不再定于，但是被订阅者还可以可以发。
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER);

        //创建观察者
        FlowableSubscriber<String> subscriber = new FlowableSubscriber<String>() {
            // 订阅关系
            private Subscription subscription;

            @Override
            public void onSubscribe(Subscription s) {
                s.request(Long.MAX_VALUE);
                subscription = s;
            }

            @Override
            public void onNext(String s) {
                //onNext事件处理
                log.info("观察者接收数据:{}", s);
                if ("test3".equals(s)) {
                    log.info("不再订阅");
                    subscription.cancel();
                }
            }

            @Override
            public void onError(Throwable t) {
                //onError事件处理
            }

            @Override
            public void onComplete() {
                //onComplete事件处理
                log.info("收到onComplete,不再订阅");
            }
        };

        // 如果不订阅，数据不会发送，也就是不执行
        flowable.subscribe(subscriber);

        // 多线程
        flowable
            .observeOn(Schedulers.io())// 让生产者发送数据在io线程
            .subscribeOn(Schedulers.computation())//让生产者发送数据在线程
            .subscribe(subscriber);
    }

    @Test
    public void CreateObservable() {
        // just 最多可以支持10个参数
        Observable.just("A")
                  .subscribe(System.out::println);

        // just把list看为一个元素
        List<String> list = Arrays.asList(new String[] {"A", "B"});
        Observable.just(list)
                  .subscribe(System.out::println);

        // fromArray 也是看做一个item
        Observable.fromArray(list)
                  .subscribe(System.out::println);

        // fromIterable 多个数据item
        Observable.fromIterable(list)
                  .subscribe(System.out::println);

        // fromFuture
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> future = executorService.submit(() -> {
            log.info("future pool run");
            Thread.sleep(1000);
            return "emit a item";
        });

        Observable.fromFuture(future)
                  .subscribe(log::info);
    }

    @Test
    public void testSingle() {
        Single<Object> single = Single.create(new SingleOnSubscribe<Object>() {
            @Override
            public void subscribe(SingleEmitter<Object> singleEmitter) throws Exception {
                singleEmitter.onSuccess("emit single");
            }
        });
        single.subscribe(new SingleObserver<Object>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onSuccess(Object o) {
                log.info("成功处理single: {}", o);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });

        single.subscribe(new BiConsumer<Object, Throwable>() {
            @Override
            public void accept(Object o, Throwable throwable) throws Exception {
                //onSuccess和onError操作都在这里处理
                log.info("recive {}", o);
            }
        });
    }

    @Test
    public void testTotoFlowable() {
        Single<Object> single = Single.create(new SingleOnSubscribe<Object>() {
            @Override
            public void subscribe(SingleEmitter<Object> singleEmitter) throws Exception {
                singleEmitter.onSuccess("emit single");
            }
        });
        single.toFlowable()
              .subscribe(new Consumer<Object>() {
                  @Override
                  public void accept(Object o) throws Exception {
                      log.info("flowable get {}", o);
                  }
              });
        single.toFlowable().subscribe(System.out::println);

    }

    @Test
    public void testMaybe() {
        //被观察者
        Maybe<String> maybe = Maybe.create(new MaybeOnSubscribe<String>() {
            @Override
            public void subscribe(MaybeEmitter<String> e) throws Exception {
                log.info("maybe emit item");
                e.onSuccess("test");//发送一个数据的情况，或者onError，不需要再调用onComplete(调用了也不会触发onComplete回调方法)
                //e.onComplete();//不需要发送数据的情况，或者onError
            }
        });
        maybe.subscribe();
    }

    @Test
    public void testJust() {
        // null会排除异常
        Observable.just(null)
                  .subscribe(System.out::println);

        Observable.just(null, "A")
                  .subscribe(System.out::println);
        // 都不打印
        Observable.just("A", null, "B")
                  .subscribe(System.out::println);
    }
    @Test
    public void subscribeTest() {
        //subscribe():告诉发送者发送，但不关心数据
        Observable.range(1, 100)
                  .subscribe();

        // 只关心onNext事件，如果发送异常直接挂
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new RuntimeException());
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                log.info("我只关心onNext事件：{}",integer);
            }
        });


    }

}
