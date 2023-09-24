package com.thoreau.algorithm.sort;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * 2023/1/2 16:02.
 *
 * @author zhaozhou
 */
public class MergeSortTest{
    @Test
    public void test() {
        int[] array = {7,2,6,5,1,8,0,3,44,32};
        MergeSort.mergeSort(array);
        assertEquals("[0, 1, 2, 3, 5, 6, 7, 8, 32, 44]", Arrays.toString(array));
    }

}