package com.thoreau.rxjava.schedulers;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.thoreau.rxjava.base.Log;
import org.junit.Test;
import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * 2018/3/28 14:20.
 *
 * @author zhaozhou
 */
public class SchedulerTest {
    ExecutorService poolA = newFixedThreadPool(10, threadFactory("Sched-A-%d"));
    Scheduler schedulerA = Schedulers.from(poolA);
    ExecutorService poolB = newFixedThreadPool(10, threadFactory("Sched-B-%d"));
    Scheduler schedulerB = Schedulers.from(poolB);
    ExecutorService poolC = newFixedThreadPool(10, threadFactory("Sched-C-%d"));
    Scheduler schedulerC = Schedulers.from(poolC);

    private ThreadFactory threadFactory(String pattern) {
        return new ThreadFactoryBuilder()
                .setNameFormat(pattern)
                .build();
    }

    @Test
    public void baseSchedulerTest() throws InterruptedException {
        Observable.create(e -> {
            Log.info("create");
            e.onNext("1");
            e.onNext("2");
            e.onNext("3");
            e.onCompleted();
        }).subscribeOn(Schedulers.io())
                  .observeOn(Schedulers.computation())
                  .subscribe(i -> Log.info("onNext : i= " + i));
        Thread.sleep(1000);
    }
    @Test
    public void baseTest() throws InterruptedException {
        Observable.just("Hello","rxJava")
                  .subscribeOn(Schedulers.io())
                  .subscribe(System.out::println);

    }



    @Test
    public void multiplySubscribeOnTest() {
        log("Starting");
        Observable<String> obs = simple();
        log("Created");
        obs.subscribeOn(schedulerA)
           .subscribeOn(schedulerB)//schedulerB 被忽略
           .subscribe(
                   x -> log("Got " + x),
                   Throwable::printStackTrace,
                   () -> log("Completed")
           );
        log("Exiting");
    }

    @Test
    public void afterSubscribeOnTest() {
        log("Starting");
        final Observable<String> obs = simple();
        log("Created");
        obs.doOnNext(this::log)
           .map(x -> x + '1')
           .doOnNext(this::log)
           .map(x -> x + '2')
           .subscribeOn(schedulerA)
           .doOnNext(this::log)// subscribeOn之后的还是subscribeOn，都调度到A
           .subscribe(
                   x -> log("Got " + x),
                   Throwable::printStackTrace,
                   () -> log("Completed")
           );
        log("Exiting");
    }

    @Test
    public void flatMapScheduleTest() throws InterruptedException {
        // 一个流，一个线程中执行
        Observable
                .just("bread", "butter", "milk", "tomato", "cheese")
                .subscribeOn(schedulerA) //BROKEN!!!
                .map(prod -> doPurchase(prod, 1))
                .reduce(BigDecimal::add)
                .single()
                .subscribe(System.out::println);

        //一个线程中执行
        Observable
                .just("bread", "butter", "milk", "tomato", "cheese")
                .subscribeOn(schedulerA) //BROKEN!!!
                .flatMap(prod -> purchase(prod, 1))
                .reduce(BigDecimal::add)
                .single()
                .subscribe(System.out::println);

        Observable
                .just("bread", "butter", "milk", "tomato", "cheese")
                .subscribeOn(schedulerA) //BROKEN!!!
                .flatMap(prod -> purchase(prod, 1).subscribeOn(schedulerA))
                .reduce(BigDecimal::add)
                .single()
                .subscribe(System.out::println);
        Thread.sleep(1000);

    }

    Observable<BigDecimal> purchase(String productName, int quantity) {
        return Observable.fromCallable(() -> doPurchase(productName, quantity));
    }
    BigDecimal doPurchase(String productName, int quantity) {
        log("Purchasing " + quantity + " " + productName);
        //real logic here
        log("Done " + quantity + " " + productName);
        return new BigDecimal(quantity);
    }

    public void log(Object msg) {
        System.out.println(
                System.currentTimeMillis() + "\t| " +
                        Thread.currentThread().getName() + "\t| " +
                        msg);
    }

    Observable<String> simple() {
        return Observable.create(subscriber -> {
            log("Subscribed");
            subscriber.onNext("A");
            subscriber.onNext("B");
            subscriber.onCompleted();
        });
    }
}
