package com.thoreau.algorithm.offer;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 2023/4/21 19:22.
 *
 * @author zhaozhou
 */
public class RobotMove {
    /**
     * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
     * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
     * 也不能进入行坐标和列坐标的数位之和大于k的格子。
     * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
     *
     */
    public int movingCount(int m, int n, int k) {
        int[][] ints = new int[m][n];
        return move(ints, 0, 0, k);
    }
    public int move(int[][] ints ,int i, int j, int k) {
        if (i < 0 || j < 0 || i > ints.length - 1 || j > ints[0].length - 1) {
            return 0;
        }
        if (getNumberSum(i) + getNumberSum(j) > k) {
            return 0;
        }
        if (ints[i][j] == 1) {
            return 0;
        }
        ints[i][j] =1;
        return move(ints, i, j + 1, k)
                + move(ints, i, j - 1, k)
                + move(ints, i - 1, j, k)
                + move(ints, i + 1, j, k)
                + 1;
    }

    /**
     * 1203
     * 1* 1000 +2 * 100 +0+10 + 3*
     * @param number
     * @return
     */

    public int getNumberSum(int number) {
        int i = 10;
        int sum = 0;
        while (number>0) {
            sum += number % 10; // 取余得到最后一位数字
            number /= 10; // 整除10，将最后一位数字删除
        }
        return sum;
    }
    public int movingCount2(int m, int n, int k) {
        int[][] ints = new int[m][n];
        Queue<int[]> queue= new LinkedList<>();
        queue.add(new int[]{0, 0});
        int count = 0;
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            if (node[0] >= m || node[1] >= n) {
                continue;
            }
            if (ints[node[0]][node[1]] == 1) {
                continue;
            }
            if (getNumberSum(node[0]) + getNumberSum(node[1]) > k) {
                continue;
            }
            ints[node[0]][node[1]] = 1;
            count++;
            queue.add(new int[]{node[0], node[1] + 1});
            queue.add(new int[]{node[0]+1, node[1]});
        }
        return count;
    }

}
