package com.thoreauz.bootlearn.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 2019/3/23 10:28 PM.
 *
 * @author zhaozhou
 */
public class StringUtilsTest {

    @Test
    public void isEmpty() {
        assertFalse(StringUtils.isEmpty("hello"));
        assertTrue(StringUtils.isEmpty(""));
        assertTrue(StringUtils.isEmpty("  "));
        assertTrue(StringUtils.isEmpty(null));
    }
    @Test
    public void hasLengthTest() {
        assertTrue(StringUtils.hasLength("hello"));
        assertFalse(StringUtils.hasLength(null));
        assertFalse(StringUtils.hasLength(""));
    }
}