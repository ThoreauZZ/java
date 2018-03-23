package com.thoreau.algorithm.array;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FindMatrixTest {
    @Test
    public void testFind() {
        int[][] array = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        assertTrue(FindMatrix.find(4, array));
        assertFalse(FindMatrix.find(3, array));
        assertFalse(FindMatrix.find(-1, array));
        assertFalse(FindMatrix.find(1000, array));
    }

    @Test
    public void testCase() {
        int i = 2;
        switch (i) {
            case 1:
                System.out.println("case1");
                return;
            case 2:
                System.out.println("case2");
                break;
            default:
                System.out.println("default");
        }
        System.out.println("done");
    }
}