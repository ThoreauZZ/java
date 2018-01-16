package com.thoreau;

/**
 * Hello world!
 */

/**
 * [
    [1,   3,  5,  7],
    [10, 11, 16, 20],
    [23, 30, 34, 50]
 ]
 */
public class App {
    public static boolean findInMatrix(int target, int[][] matrix) {
        if (matrix == null) {
            return false;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean find = false;
        if (rows > 0 && cols > 0) {
            int row = 0;
            int col = cols - 1;
            while (row < rows && col >= 0) {
                if (target == matrix[row][col]) {
                    find = true;
                    break;
                }
                if (target < matrix[row][col]) {
                    col--;
                } else {
                    row++;
                }
            }
        }
        return find;
    }
}
