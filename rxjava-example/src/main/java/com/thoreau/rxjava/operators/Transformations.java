package com.thoreau.rxjava.operators;

import com.thoreau.rxjava.base.Log;
import org.junit.Test;
import rx.Observable;

/**
 * 2018/3/27 17:40.
 *
 * @author zhaozhou
 */
public class Transformations {
    @Test
    public void mapTest() {
        Observable.just("zz", "Thoreau")
                  .map(String::toUpperCase)
                  .subscribe(System.out::println);

        Observable.just(8, 9, 10)
                  .filter(i -> i % 3 > 0)
                  .map(i -> "#" + i * 10)
                  .filter(s -> s.length() < 4)
                  .subscribe(System.out::println);

        System.out.println("-----------------");
        Observable
                .just(8, 9, 10)
                .doOnNext(i -> System.out.println("A: " + i))
                .filter(i -> i % 3 > 0)
                .doOnNext(i -> System.out.println("B: " + i))
                .map(i -> "#" + i * 10)
                .doOnNext(s -> System.out.println("C: " + s))
                .filter(s -> s.length() < 4)
                .subscribe(s -> System.out.println("D: " + s));
        // 订阅up到最前，然后一个一个数据处理
    }

    @Test
    public void flatMapTest() {
        //transformation of each element can return another Observable
        Observable.just(1, 2, 3)
                  .flatMap(x -> Observable.just(x * 2))
                  .subscribe(System.out::println);

        System.out.println("------------------------------------");

        Observable.just(1, 2, 3)
                  .flatMap(x -> Observable.create(subscriber -> {
                      Log.info("falt-->"+x + " x 2");
                      subscriber.onNext(x * 2);
                  }))
                  .subscribe(System.out::println);

    }
}
