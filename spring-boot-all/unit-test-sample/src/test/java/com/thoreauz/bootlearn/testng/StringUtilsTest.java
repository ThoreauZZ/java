package com.thoreauz.bootlearn.testng;

import org.testng.annotations.Test;

/**
 * 2018/4/20 下午11:50.
 *
 * @author zhaozhou
 */
public class StringUtilsTest {
    @Test
    public void isEmptyTest() {
        System.out.println("test isEmptyTest");
        assert StringUtils.isBlank(null);
        assert StringUtils.isBlank("");
    }

    @Test
    public void trimTest() {
        System.out.println("test trimTest");
        assert "foo".equals(StringUtils.trim("  foo   "));
    }

}