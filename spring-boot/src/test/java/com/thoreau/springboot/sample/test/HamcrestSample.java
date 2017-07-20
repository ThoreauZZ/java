package com.thoreau.springboot.sample.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.greaterThan;

/**
 * 17/7/19 下午10:34.
 *
 * @author zhaozhou
 */
public class HamcrestSample {
    @Test
    public void sampleTest() {
        int a = 12;
        assertThat(a, is(12));
        assertThat(a, not(11));
        assertThat(a, greaterThan(1));

        String str = "Hello World";
        assertThat(str,containsString("Hello"));
        assertThat(str,equalToIgnoringCase("hello world"));

        List<String> stringList = new ArrayList<>();
        stringList.add("e1");
        stringList.add("e2");
        assertThat(stringList, hasItem("e1"));
        assertThat(stringList, anything());
    }

}
