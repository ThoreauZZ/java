package com.thoreauz.bootlearn.testng.dataprovider;

import org.testng.annotations.Test;

/**
 * 2018/4/21 上午1:03.
 *
 * @author zhaozhou
 */
public class TestClass {
    @Test(dataProvider = "data-provider", dataProviderClass = DataProviderClass.class)
    public void testMethod(String data) {
        System.out.println("Data is: " + data);
    }
}
