package com.thoreau.algorithm.dynamicprogramming;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 2023/6/10 22:53.
 *
 * @author zhaozhou
 */
public class DP01Test {

    private DP01 dp = new DP01();

    @Test
    public void fibonacciTest() {
//        assertEquals(1, dp.fibonacci(2));
        assertEquals(2, dp.fibonacci(3));
//        assertEquals(3, dp.fibonacci2(4));
//        assertEquals(5, dp.fibonacci2(5));
//        assertEquals(8, dp.fibonacci(6));
//        assertEquals(13, dp.fibonacci(7));
    }

    @Test
    public void cuttingRopeTest() {
        assertEquals(1, dp.cuttingRope(2));
        assertEquals(36, dp.cuttingRope(10));
    }
    @Test
    public void maxValueTest() {
        int[][] array = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        assertEquals(12, dp.maxValue(array));
    }
    @Test
    public void maxProfitTest() {
        int[] array = {

                7,6,5
        };
        assertEquals(0, dp.maxProfit(array));
    }
    @Test
    public void countDigitOneTest() {
        assertEquals(5, dp.countDigitOne(12));
    }
}