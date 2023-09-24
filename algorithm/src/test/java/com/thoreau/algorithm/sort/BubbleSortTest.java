package com.thoreau.algorithm.sort;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * 2023/1/1 10:52.
 *
 * @author zhaozhou
 */
public class BubbleSortTest {
    @Test
    public void test() {
        int[] a = new int[]{9, 3, 6, 1, 0, 4, 7};
        assertEquals("[0, 1, 3, 4, 6, 7, 9]", Arrays.toString(BubbleSort.bubbleSort(a)));
    }
}