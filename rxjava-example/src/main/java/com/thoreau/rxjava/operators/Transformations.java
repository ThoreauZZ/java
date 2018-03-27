package com.thoreau.rxjava.operators;

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

        Observable
                .just(8, 9, 10)
                .doOnNext(i -> System.out.println("A: " + i))
                .filter(i -> i % 3 > 0)
                .doOnNext(i -> System.out.println("B: " + i))
                .map(i -> "#" + i * 10)
                .doOnNext(s -> System.out.println("C: " + s))
                .filter(s -> s.length() < 4)
                .subscribe(s -> System.out.println("D: " + s));
    }
}
