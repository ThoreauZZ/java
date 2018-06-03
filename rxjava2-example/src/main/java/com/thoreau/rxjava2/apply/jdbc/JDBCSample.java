package com.thoreau.rxjava2.apply.jdbc;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;
import lombok.Data;
import org.davidmoten.rx.jdbc.Database;
import org.davidmoten.rx.jdbc.Tx;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * 2018/6/3 下午8:57.
 *
 * @author zhaozhou
 */
public class JDBCSample {

    private static Database db;

    @Before
    public void init() {
        db = Database.test();
    }

    @Test
    public void testSelect() {
        String sql = "select name from person";
        Single<List<String>> personNames = getPersonNames(sql);

        // blocking get:  Observables to Collections
        List<String> list = personNames.blockingGet();
        list.forEach(System.out::println);




    }

    @Test
    public void testMulti() {
        long l1 = System.currentTimeMillis();

        db.select("select name from person")
          .transactedValuesOnly()
          .getAs(String.class)
          .flatMap(tx -> tx
                  .select("select score from person where name=:name")
                  .parameter("name", tx.value())
                  .valuesOnly()
                  .getAs(Integer.class)
          ).toList().blockingGet().forEach(System.out::println);
        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1);
    }

    @Test
    public void testR() {
        BiFunction<String, Integer, Object> createPersonFunc = new BiFunction<String, Integer, Object>() {
            @Override
            public Object apply(String s, Integer integer) throws Exception {
                Person person = new Person();
                person.setName(s);
                person.setScore(integer);
                return person;
            }
        };
        long l1 = System.currentTimeMillis();
        getAllPersonNames()
                .flatMap(tx -> Flowable.just(tx.value())
                                       .zipWith(getScoreByName(tx.value()), createPersonFunc)
                )
                .blockingForEach(System.out::println);
        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1);

    }


    public Flowable<Integer> getScoreByName(String name) {
        return db.select("select score from person where name=:name")
                 .parameter("name", name)
                 .getAs(Integer.class);
    }

    public Flowable<Tx<String>> getAllPersonNames() {
        return db.select("select name from person")
                 .transactedValuesOnly()
                 .getAs(String.class);
    }


    @Data
    class Person {
        private Integer id;
        private String name;
        private Integer score;
    }


    public Single<List<String>> getPersonNames(String sql) {
        return db.select(sql).getAs(String.class).toList();
    }
}
