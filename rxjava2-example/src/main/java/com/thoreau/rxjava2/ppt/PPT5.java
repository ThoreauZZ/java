package com.thoreau.rxjava2.ppt;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;

/**
 * 2018/6/3 下午4:23.
 *
 * @author zhaozhou
 */
@Slf4j
public class PPT5 {


    public static void main(String[] args) {
        Observable.create(emitter -> {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onComplete();
        }).observeOn(Schedulers.io())
                  .subscribe(o -> log.info("receive data: {}", o));

    }
}
