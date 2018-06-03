package com.thoreau.rxjava2.ppt;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.reactivex.Observable.defer;

/**
 * 2018/6/3 下午4:23.
 *
 * @author zhaozhou
 */
@Slf4j
public class PPT7 {



    @Test
    public void test() {
        // createList()方法不会等到订阅才执行
        Observable.fromIterable(createList());

        // createList()在subscribe订阅后才执行
        defer(() -> Observable.fromIterable(createList()))
                  .subscribe();

    }
    private List<String> createList() {
        System.out.println("create list");
        return Arrays.asList("the", "quick", "brown",
                "fox", "jumped", "over", "the", "lazy", "dog");
    }

    @Test
    public void testNoLazy() {
        //StackOverflowError
//        allString(1).subscribe(System.out::println);
        allString(1)
                .take(100)
                .subscribe(System.out::println);

    }
    Observable<String> allString(int initialPage) {
        return defer(() -> listString(initialPage))
                .concatWith(defer(() -> allString(initialPage + 1)));
    }
    public Observable<String> listString(int page){
        List<String> strings = new ArrayList<>();
        int size = 5;
        for (int i = page*size; i < page*(size + 1) && i <1000; i++) {
            strings.add("data" + i);
        }
       return Observable.fromIterable(strings);
    }
    public Observable<Book> bestSeller(){
        // 模拟调用
        System.out.println("get book from book bestSellerService..");
        Book book = new Book();
        return Observable.just(book);
    }

}
