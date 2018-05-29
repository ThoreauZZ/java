package com.thoreau.rxjava2.base;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 2018/5/29 下午10:57.
 *
 * @author zhaozhou
 */
@Slf4j
public class CaseMultiRpc {
    private ExecutorService executorService;


    @Before
    public void init() {
        executorService = Executors.newFixedThreadPool(3);
    }

    @Test
    public void test() throws InterruptedException {
        Result result = new Result();
        CountDownLatch countDownLatch = new CountDownLatch(2);

        Flowable<Location> location = getLocation(1);
        Flowable<String> userName = getUserName(3);
        long l1 = System.currentTimeMillis();
        //        Flowable.just(userName, location) 只要有一个抛出异常，就完蛋了，后续的订阅无效
        Flowable.just(userName, location)
                .flatMap(flowable -> flowable.map(item -> {
                    if (item instanceof Location) {
                        Location l = (Location) item;
                        result.setLocation(l.getAddress());
                        log.info("是location {}", item);
                    } else {
                        String name = (String) item;
                        result.setName(name);
                        log.info("是String {}", item);
                    }
                    return item;
                }))
                .subscribeOn(Schedulers.computation())
                // 最后发起订阅才会执行flatMap
                .subscribe( o -> {
                    log.info("订阅结果{}",o);
                    countDownLatch.countDown();
                },e->{
                    log.error("出现异常",e.getMessage());
                    countDownLatch.countDown();
                },()->{
                    countDownLatch.countDown();
                });
        countDownLatch.await();

        long l2 = System.currentTimeMillis();
        log.info("结束，rst={},时间：{}",result,l2-l1);
    }
    @Test
    public void test2() throws InterruptedException {
        Result result = new Result();
        CountDownLatch countDownLatch = new CountDownLatch(2);

        Flowable<Location> location = getLocation(1);
        Flowable<String> userName = getUserName(2);
        long l1 = System.currentTimeMillis();
        List<Flowable> flowableList = new ArrayList<>();
        flowableList.add(userName);
        flowableList.add(location);
//        RxJavaPlugins.onError(new Throwable());
        Flowable.fromIterable(flowableList)
                .flatMap(flowable -> flowable.map(item -> {
                    if (item instanceof Location) {
                        Location l = (Location) item;
                        result.setLocation(l.getAddress());
                        log.info("是location {}", item);
                    } else {
                        String name = (String) item;
                        result.setName(name);
                        log.info("是String {}", item);
                    }
                    return item;
                }))
                .onErrorResumeNext(Flowable.just("hard"))
                // 最后发起订阅才会执行flatMap
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.computation())
                .subscribe( o -> {
                    log.info("订阅结果{}",o);
                    countDownLatch.countDown();
                },e->{
                    log.info("出现异常");
                    countDownLatch.countDown();
                },()->{
//                    countDownLatch.countDown();
                });
        countDownLatch.await();
        long l2 = System.currentTimeMillis();
        log.info("结束，rst={},时间：{}",result,l2-l1);
    }

    public Flowable<String> getUserName(int userId) {
        Future<String> future = executorService.submit(() -> {
            log.info("异步调用用户模块");
            Thread.sleep(1000);
            log.info("----end:异步调用用户模块---------");
            if (userId == 1) {
                return "thorea";
            } else if (userId == 2) {
                throw new RuntimeException();
            } else {
                return null;
            }
        });
        return Flowable.fromFuture(future);
    }
    public Flowable<Location> getLocation(int userId) {
        Future<Location> future = executorService.submit(() -> {
            log.info("异步调用地址模块");
            Thread.sleep(3000);
            log.info("----end:异步调用地址模块---------");
            return new Location(12, "北京");
        });
        return Flowable.fromFuture(future);
    }
    @Data
    class Location {
        public Location(int code, String address) {
            this.address = address;
            this.code = code;
        }
        private int code;
        private String address;
    }
    @Data
    class Result {
        private String location;
        private int id;
        private String name;
    }
}
