package com.thoreau.springboot.sample.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * 17/7/17 上午10:14.
 *
 * @author zhaozhou
 */
//@DisplayName junit 5 之后提供
public class JunitTestSample {

    @Before
    public void setUp() throws Exception {
        System.out.println("this is setUp()...");
    }
    @After
    public void tearDown() throws Exception {
        System.out.println("this is tearDown()...");
    }
    @Test
    public void testEquals(){
        String str = "hi, Junit";
        assertEquals(str, "hi, Junit");
    }
    @Test
    public void testNull(){
        String str;
        str = null;
        assertNull(str);
    }
}
