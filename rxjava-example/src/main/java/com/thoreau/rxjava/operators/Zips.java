package com.thoreau.rxjava.operators;

import com.thoreau.rxjava.base.Log;
import org.junit.Test;
import rx.Observable;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static rx.Observable.interval;


/**
 * 2018/3/29 11:52.
 *
 * @author zhaozhou
 */
public class Zips {
    @Test
    public void zipWithTest() {
        Observable.just(1, 2, 3, 4)
                  .zipWith(Observable.just("A", "B", "C"), (x, y) -> x + y)
                  .subscribe(System.out::println);
    }

    @Test
    public void combineTest() throws InterruptedException {
        Observable.combineLatest(
                interval(20, MILLISECONDS).map(x -> "S" + x),
                interval(10, MILLISECONDS).map(x -> "F" + x),
                (s, f) -> f + ":" + s
        ).forEach(System.out::println);
        Thread.sleep(40);

        System.out.println("---------------");
        Observable<String> fast = interval(10, MILLISECONDS).map(x -> "F" + x);
        Observable<String> slow = interval(17, MILLISECONDS).map(x -> "S" + x);
        slow.withLatestFrom(fast, (s, f) -> s + ":" + f)
            .forEach(System.out::println);
        Thread.sleep(40);
    }
    @Test
    public void ambTest() {
        Observable.amb(
                stream(100, 17, "S"),
                stream(200, 10, "F")
        ).subscribe(Log::info);
    }

    Observable<String> stream(int initialDelay, int interval, String name) {
        return Observable
                .interval(initialDelay, interval, MILLISECONDS)
                .map(x -> name + x)
                .doOnSubscribe(() -> Log.info("Subscribe to " + name))
                .doOnUnsubscribe(() -> Log.info("Unsubscribe from " + name));
    }
}
