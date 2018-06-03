package com.thoreau.rxjava2.ppt;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;

/**
 * 2018/6/3 下午4:23.
 *
 * @author zhaozhou
 */
@Slf4j
public class PPT4 {
    public static void main(String[] args) {
        // 创建被观察者
        Observable<Integer> observable = Observable.create(emitter -> {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
            emitter.onComplete();
        });

        // 创建被观察者
        Observer<Integer> observer = new Observer<Integer>() {
            private Disposable disposable;
            @Override
            public void onSubscribe(Disposable disposable) {
                this.disposable = disposable;
            }

            @Override
            public void onNext(Integer o) {
                if (o == 2) {
                    disposable.dispose();
                }
                log.info("receive data: {}",o);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {
                log.info("complete ...");
            }
        };

        // 订阅
        observable.subscribe(observer);
    }
}
