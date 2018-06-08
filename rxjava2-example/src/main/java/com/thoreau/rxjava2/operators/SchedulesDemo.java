package com.thoreau.rxjava2.operators;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 2018/6/7 下午10:03.
 *
 * @author zhaozhou
 */
public class SchedulesDemo {
    @Test
    public void testSchdules() throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(200);
        CountDownLatch finishedLatch = new CountDownLatch(1);
        long start = System.currentTimeMillis();
        Observable.range(0, 100)
                  .map(item -> {
                      try {
                          // do something here
                          Thread.sleep(20);
                      } catch (InterruptedException e) {
                          return Observable.error(e);
                      }
                      return item + "map";
                  })
                  .observeOn(Schedulers.from(es))
                  .subscribeOn(Schedulers.computation())
                  .subscribe(item -> { }, (Throwable error) -> { }, finishedLatch::countDown);
        finishedLatch.await();
        System.out.println((System.currentTimeMillis() - start) + "");
    }
    @Test
    public void testSchdulesWithFlatmap() throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(200);
        CountDownLatch finishedLatch = new CountDownLatch(1);
        long start = System.currentTimeMillis();
        Observable.range(0, 100)
                  .flatMap(rangeItem -> Observable
                          .just(rangeItem)
                          .subscribeOn(Schedulers.from(es))
                          .map(i -> {
                              try {
                                  Thread.sleep(20);
                              } catch (InterruptedException e) {
                                  return Observable.error(e);
                              }
                              return i + "map";
                          }))
                  .observeOn(Schedulers.computation())
                  .subscribe(item -> { }, (Throwable error) -> { }, finishedLatch::countDown);
        finishedLatch.await();
        System.out.println("-------------");
        System.out.println((System.currentTimeMillis() - start) + "");
    }
}
