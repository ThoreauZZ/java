package com.thoreau.rxjava;

import org.junit.Test;
import rx.Observable;
import rx.Subscription;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

/**
 * 2018/3/26 19:23.
 *
 * @author zhaozhou
 */
public class CreateObservable {
    @Test
    public void createTest() {
        // just最多接受10个值
        Observable.just("Hello").subscribe(System.out::println);
        Observable.just("Hello", "RxJava").subscribe(System.out::println);
        Observable.just("just","delay")
                  .delay(10, TimeUnit.SECONDS)//操作符
                  .subscribe(System.out::println);


        // timer()
        Observable.timer(1, TimeUnit.SECONDS)
                .subscribe((Long zero) -> log(zero));
        // interval()
        Observable
                .interval(1_000_000 / 60, TimeUnit.MICROSECONDS)
                .subscribe((Long i) -> log(i));

    }
    @Test
    public void coldHotTest() {
        // Observable.crete(),from(),range(),是冷Observable，只有订阅者对他感兴趣，才会执行，之前只是一个数据结构。依赖订阅者。
        // 1. 冷数据，从头到尾获取全部数据，相反，不能保证。
    }

    @Test
    public void multipleSubscribersTest() {
        Observable<Integer> ints =
                Observable.create(subscriber -> {
                            log("Create");
                            subscriber.onNext(42);
                            subscriber.onCompleted();
                        }
                );
        log("Starting");
        ints.subscribe(i -> log("Element A: " + i));
        ints.subscribe(i -> log("Element B: " + i));
        log("Exit");
        /*
        main: Starting
        main: Create
        main: Element A: 42
        main: Create
        main: Element B: 42
        main: Exit
         */
        // 每个订阅中都创建一个数据
    }

    @Test
    public void multipleSubscribersCacheTest() {
        Observable<Object> observable = Observable.create(subscriber -> {
                    log("Create");
                    subscriber.onNext(42);
                    subscriber.onCompleted();
                }
        ).cache();
        log("Starting");
        observable.subscribe(i -> log("Element A: " + i));
        observable.subscribe(i -> log("Element B: " + i));
        log("Exit");
        /*
            main: Starting
            main: Create
            main: Element A: 42
            main: Element B: 42
            main: Exit
         */
        // 使用cache: Operators第一个操作符；使用时小心OutOfMemoryError.
    }

    @Test
    public void infiniteStreamTest() throws InterruptedException {
        Observable<BigInteger> naturalNumbers = Observable.create(
                subscriber -> {
                    Runnable r = () -> {
                        BigInteger i = ZERO;
                        // isUnsubscribed比较有用，如果观察者不需要，就无需再发送数据。
                        while (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(i);
                            log("emit "+i);
                            i = i.add(ONE);
                        }
                    };
                    new Thread(r).start();
                });
        Subscription subscription = naturalNumbers.subscribe(x -> log(x));
        //after some time...
        Thread.sleep(10);
        subscription.unsubscribe();
        log("unsubscribe");
        Thread.sleep(100);

    }

    @Test
    public void executeBlockTest() {
        log("Before");
        Observable
                .range(5, 3)
                .subscribe(i -> {
                    log(i);
                });
        log("After");
    }

    @Test
    public void executeOrderTest() {
        Observable<Integer> ints = Observable.create(subscriber -> {
            log("Create");
            subscriber.onNext(5);
            subscriber.onNext(6);
            subscriber.onNext(7);
            subscriber.onCompleted();
            log("Completed");
        });
        log("Starting");
        ints.subscribe(i -> log("Element: " + i));
        log("Exit");
        /*
        main: Starting
        main: Create
        main: Element: 5
        main: Element: 6
        main: Element: 7
        main: Completed
        main: Exit
         */
        // ints只是创建了对象，并没有执行匿名函数知道有订阅者。冷数据

    }

    private static void log(Object msg) {
        System.out.println(Thread.currentThread().getName() + ": " + msg);
    }
}
