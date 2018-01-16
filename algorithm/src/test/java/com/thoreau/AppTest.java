package com.thoreau;

import org.junit.Test;

/**
 * Unit test for simple App.
 *     [1,   3,  5,  7],
 [10, 11, 16, 20],
 [23, 30, 34, 50]
 */
public class AppTest {



    @Test
    public void testFind() {
        int target = 3;
        int[][] matrix = new int[][]{{1, 3, 5, 7},{10, 11, 16, 20},{23, 30, 34, 50}};
        System.out.println(App.findInMatrix(3, matrix));

        System.out.println(App.findInMatrix(4, matrix));
        System.out.println(App.findInMatrix(-1, matrix));
    }
}
