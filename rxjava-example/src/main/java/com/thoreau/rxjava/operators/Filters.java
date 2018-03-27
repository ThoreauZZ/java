package com.thoreau.rxjava.operators;

import org.junit.Test;
import rx.Observable;
import rx.functions.Func1;

/**
 * 2018/3/27 17:42.
 *
 * @author zhaozhou
 */
public class Filters {
    @Test
    public void filterTest() {
        Observable.just(1, 2, 3, 4, 5)
                  .filter(new Func1<Integer, Boolean>() {
                      @Override
                      public Boolean call(Integer item) {
                          return (item < 4);
                      }
                  }).subscribe(System.out::println);
        // lambdas
        Observable.just(1, 2, 3, 4, 5)
                  .filter(item -> (item < 4))
                  .subscribe(System.out::println);
    }
    @Test
    public void distinctTest() {
        Observable.just(1, 2, 1, 1, 2, 3)
                  .distinct()
                  .subscribe(System.out::println);

    }
    @Test
    public void firstTest() {
        Observable.just(1, 2, 1, 1, 2, 3)
                  .first()
                  .subscribe(System.out::println);

    }

}
