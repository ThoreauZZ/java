package com.thoreau.rxjava2.ppt;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;

/**
 * 2018/6/3 下午4:23.
 *
 * @author zhaozhou
 */
@Slf4j
public class PPT1 {
    public static void main(String[] args) {
        // 创建被观察者
        Observable<Object> observable = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onComplete();
            }
        });

        // 创建被观察者
        Observer<Object> observer = new Observer<Object>() {

            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(Object o) {
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
