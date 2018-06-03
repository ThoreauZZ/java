package com.thoreau.rxjava2.ppt;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * 2018/6/3 下午4:23.
 *
 * @author zhaozhou
 */
@Slf4j
public class PPT3 {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("the", "quick", "brown",
                "fox", "jumped", "over", "the", "lazy", "dog");

        // rxjava
        Observable.fromIterable(words)
                  .flatMap(word -> Observable.fromArray(word.split("")))
                  .distinct()
                  .sorted()
                  .zipWith(Observable.range(1, Integer.MAX_VALUE),
                          (string, count) -> String.format("%2d. %s", count, string))
                  .subscribe(System.out::println);

        // java8 stream
        words.stream()
             .flatMap((Function<String, Stream<?>>) s -> Arrays.stream(s.split("")))
             .distinct()
             .sorted()
             .forEach(System.out::println);

    }
}
