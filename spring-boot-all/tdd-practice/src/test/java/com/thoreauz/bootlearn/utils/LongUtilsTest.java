package com.thoreauz.bootlearn.utils;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;


/**
 * 2019/3/24 12:37 AM.
 *
 * @author zhaozhou
 */
public class LongUtilsTest {
    @Test
    public void isZeroOrNullTest(){
        assertTrue(LongUtils.isZeroOrNull(0L));
        assertThat(LongUtils.isZeroOrNull(0L)).isTrue();
        assertTrue(LongUtils.isZeroOrNull(null));
        assertFalse(LongUtils.isZeroOrNull(-1L));
        assertFalse(LongUtils.isZeroOrNull(2L));

    }
    @Test(dependsOnMethods = { "testTwo" })
    public void testOne() {
        System.out.println("Test method one");
    }
    @Test
    public void testTwo() {
        System.out.println("Test method two");
    }
}