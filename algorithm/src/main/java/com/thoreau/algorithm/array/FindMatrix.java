package com.thoreau.algorithm.array;

/**
 * 2017/12/16 下午10:27.
 *
 * @author zhaozhou
 */
public class FindMatrix {
    /**
     * 在二维数组中查找7在一个二维数组中，每一行都按照从左到右递增的顺序排序，
     * 每一列都按照从上到下递增的顺序排序。请完成一个函数，
     * 输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     *
     * @param n      target
     * @param matrix 所给数据
     * @return 是否找到
     */
    public static boolean find(int n, int[][] matrix) {
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
                if (n == matrix[row][col]) {
                    find = true;
                    break;
                }
                if (n < matrix[row][col]) {
                    col--;
                } else {
                    row++;
                }
            }
        }
        return find;
    }

    public static void main(String[] args) {
        int[][] array = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        System.out.println(find(4, array));
    }
}
