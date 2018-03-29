package com.thoreau.rxjava.schedulers;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Rule;
import org.junit.Test;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.github.tomakehurst.wiremock.client.WireMock.*;


/**
 * 2018/3/28 下午11:35.
 *
 * @author zhaozhou
 */
public class MultipleThread {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);


    @Test
    public void testMult() throws Exception {
        stubFor(get(urlEqualTo("/hello"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withBody("Hello world!")));
        testRxJavaWithFlatMap(200);
    }

    public void testRxJavaWithFlatMap(int count) throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(200,
                new ThreadFactoryBuilder().setNameFormat("SubscribeOn-%d").build());
        URL url = new URL("http://127.0.0.1:8999/hello");
        CountDownLatch finishedLatch = new CountDownLatch(1);
        long t = System.nanoTime();
        Observable.range(0, count)
                  .subscribeOn(Schedulers.io())
                  .flatMap(i -> Observable.just(i).
                          subscribeOn(Schedulers.from(es)).map(v -> {
                              try {
                                  HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                  conn.setRequestMethod("GET");
                                  int responseCode = conn.getResponseCode();
                                  BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                                  String inputLine;
                                  while ((inputLine = in.readLine()) != null) {
                                  }
                                  in.close();
                                  return responseCode;
                              } catch (Exception ex) {
                                  return -1;
                              }
                          }
                  )
        ).observeOn(Schedulers.computation()).subscribe((Integer statusCode) -> { }, (Throwable error) -> { }, finishedLatch::countDown);
        finishedLatch.await();
        t = (System.nanoTime() - t) / 1000000; //ms
        System.out.println("RxJavaWithFlatMap TPS: " + count * 1000 / t);
        es.shutdownNow();
    }
}
