package com.thoreau.rxjava2.operators;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 2018/6/1 下午10:39.
 *
 * @author zhaozhou
 */
@Slf4j
public class MapDemo {
    @Test
    public void test() throws InterruptedException {
        // 被观察者
        Observable.create(emitter -> {
            for (int i = 0;;i++) {
                log.info("emit " + i);
                emitter.onNext(i);
            }
        }).observeOn(Schedulers.io())
                  .map(o -> {
                      if (o instanceof Integer) {
                          Integer o1 = (Integer) o;
                          log.info("map->" + o1);
                          Thread.sleep(200);
                          return (o1 + "A");
                      }
                      return o;
                  }).subscribe(o -> log.info("接收数据: " + o));
        Thread.sleep(100);
    }

    @Test
    public void testReduce() {
        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
            emitter.onComplete();
        }).reduce((integer, integer2) -> integer + integer2)
                  .subscribe(System.out::println);
    }
}
