package com.thoreauz.bootlearn.testng.dependency;

import org.testng.annotations.Test;

/**
 * 2018/4/21 上午12:53.
 *
 * @author zhaozhou
 */
public class DependentTestExamples {
    @Test(dependsOnMethods = { "testTwo" })
    public void testOne() {
        System.out.println("Test method one");
    }

    @Test
    public void testTwo() {
        System.out.println("Test method two");
    }
    @Test(dependsOnGroups = { "test-group" })
    public void groupTestOne() {
        System.out.println("Group Test method one");
    }

    @Test(groups = { "test-group" })
    public void groupTestTwo() {
        System.out.println("Group test method two");
    }

    @Test(groups = { "test-group" })
    public void groupTestThree() {
        System.out.println("Group Test method three");
    }
}
