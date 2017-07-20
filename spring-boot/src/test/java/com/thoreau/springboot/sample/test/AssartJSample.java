package com.thoreau.springboot.sample.test;

import com.github.tomakehurst.wiremock.common.Dates;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Index.atIndex;
import static org.assertj.core.util.Lists.newArrayList;

/**
 * 17/7/17 上午11:11.
 *
 * @author zhaozhou
 */
public class AssartJSample {
    @Test
    public void assartJTest() {
        assertThat("Frodo").isEqualTo("Frodo").isEqualToIgnoringCase("frodo");
        assertThat(42).isGreaterThan(38).isGreaterThanOrEqualTo(38);
        assertThat(Dates.parse("2014-02-01"))
                .isEqualTo("2014-02-01")
                .isNotEqualTo("2014-01-01")
                .isAfter("2014-01-01").isBefore(Dates.parse("2014-03-01"));
        assertThat(newArrayList(1, 2, 3))
                .contains(1, atIndex(0))
                .contains(2, atIndex(1))
                .contains(3).isSorted();
        assertThat(RequestMapping.class).isAnnotation();
        assertThat("string").isInstanceOf(String.class);
    }
}
