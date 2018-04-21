package com.thoreauz.bootlearn.testng;

import org.testng.annotations.Test;

import java.io.IOException;

/**
 * 2018/4/21 上午12:37.
 *
 * @author zhaozhou
 */
public class ExceptionTestDemo {
    @Test(expectedExceptions = {IOException.class})
    public void exceptionTestOne() throws Exception {
        throw new IOException();
    }

    @Test(expectedExceptions = {Exception.class})
    public void exceptionTestTwo() throws Exception {
        throw new Exception();
    }

    @Test(expectedExceptions = {IOException.class}, expectedExceptionsMessageRegExp = "Pass Message test")
    public void exceptionTestThree() throws Exception {
        throw new IOException("Pass Message test");
    }

    @Test(expectedExceptions = {IOException.class}, expectedExceptionsMessageRegExp = ".* Message .*")
    public void exceptionTestFour() throws Exception {
        throw new IOException("Pass Message test");
    }
}
