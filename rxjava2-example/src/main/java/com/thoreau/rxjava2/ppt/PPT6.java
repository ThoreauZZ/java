package com.thoreau.rxjava2.ppt;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.awt.print.Book;
import java.util.Arrays;
import java.util.List;

/**
 * 2018/6/3 下午4:23.
 *
 * @author zhaozhou
 */
@Slf4j
public class PPT6 {

    @Test
    public void test() {
        // createList()方法不会等到订阅才执行
        Observable.fromIterable(createList());

        // createList()在subscribe订阅后才执行
        Observable.defer(() -> Observable.fromIterable(createList()))
                  .subscribe();

    }
    private List<String> createList() {
        System.out.println("create list");
        return Arrays.asList("the", "quick", "brown",
                "fox", "jumped", "over", "the", "lazy", "dog");
    }

    @Test
    public void testNoLazy() {
        // 调用recommend推荐书籍，如果失败调用bestSeller()获取一本畅销书
        recommend(1)
                .onErrorResumeNext(bestSeller())
                .subscribe();
    }
    public Observable<Book> recommend(Integer id){
        // 模拟调用
        if (id == 1) {
            return Observable.just(new Book());
        }else {
            throw new RuntimeException();
        }
    }
    public Observable<Book> bestSeller(){
        // 模拟调用
        System.out.println("get book from book bestSellerService..");
        Book book = new Book();
        return Observable.just(book);
    }

}
