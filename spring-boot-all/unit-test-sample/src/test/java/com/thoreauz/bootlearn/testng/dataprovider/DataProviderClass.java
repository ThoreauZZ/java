package com.thoreauz.bootlearn.testng.dataprovider;

import org.testng.annotations.DataProvider;

/**
 * 2018/4/21 上午1:03.
 *
 * @author zhaozhou
 */
public class DataProviderClass {
    @DataProvider(name = "data-provider")
    public static Object[][] dataProviderMethod() {
        return new Object[][]{{"data one"}, {"data two"}};
    }
}
