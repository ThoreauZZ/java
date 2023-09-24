package com.thoreau.algorithm.sort;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * 2023/1/6 18:17.
 *
 * @author zhaozhou
 */
public class QuickSortTest {

    @Test
    public void testQuickSort() {
        int[] array = {7,2,6,5,1,8,0,3,44,32};
        QuickSort.quickSort(array);
        assertEquals("[0, 1, 2, 3, 5, 6, 7, 8, 32, 44]", Arrays.toString(array));

        int[] array2 = {7};
        QuickSort.quickSort(array);

        assertEquals("[7]", Arrays.toString(array2));

        int[] array3 = {5,4,3,2,1};
        QuickSort.quickSort(array3);
        assertEquals("[1, 2, 3, 4, 5]", Arrays.toString(array3));

        int[] array4 = {1, 2, 3, 4, 5};
        QuickSort.quickSort(array4);
        assertEquals("[1, 2, 3, 4, 5]", Arrays.toString(array4));

        int[] array5 = {5, 5, 5, 5, 5};
        QuickSort.quickSort(array4);
        assertEquals("[5, 5, 5, 5, 5]", Arrays.toString(array5));
    }
    @Test
    public void performanceTest() {
        int size = 70000;
        int[] a = new int[size];
        for (int i = 0; i < size; ++i){
            a[i] = (int )(Math.random() * 10000000 + 1);;
        }
        long l1 = System.currentTimeMillis();
        QuickSort.quickSort(a);
        long l2 = System.currentTimeMillis();
        System.out.println(l2-l1);

    }
}