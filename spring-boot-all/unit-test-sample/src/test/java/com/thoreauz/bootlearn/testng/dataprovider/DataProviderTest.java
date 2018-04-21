package com.thoreauz.bootlearn.testng.dataprovider;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 * 2018/4/21 上午1:05.
 *
 * @author zhaozhou
 */
public class DataProviderTest {
    private int param;

    @Factory(dataProvider = "dataMethod")
    public DataProviderTest(int param) {
        this.param = param;
    }

    @DataProvider
    public static Object[][] dataMethod() {
        return new Object[][] { { 0 }, { 1 } };
    }

    @Test
    public void testMethodOne() {
        int opValue = param + 1;
        System.out.println("Test method one output: " + opValue);
    }

    @Test
    public void testMethodTwo() {
        int opValue = param + 2;
        System.out.println("Test method two output: " + opValue);
    }
}
