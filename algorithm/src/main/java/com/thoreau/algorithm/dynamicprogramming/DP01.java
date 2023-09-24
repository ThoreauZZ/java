package com.thoreau.algorithm.dynamicprogramming;

/**
 * 2023/6/10 22:51.
 *
 * @author zhaozhou
 */
public class DP01 {

    public int fibonacci(int n) {
        System.out.println("fibonacci " + n);
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public int fibonacci2(int n) {
        int a = 0, b = 1, sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return sum;
    }

    public int cuttingRope(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 2; i <= n; i++) {
            int curMax = 1;
            for (int j = 0; j < i; j++) {
                curMax = Math.max(curMax, Math.max(j * (i - j), j * dp[i - j]));
            }
            dp[i] = curMax;
        }
        return dp[n];
    }

    public int maxValue(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int max = grid[0][0];
                if (j > 0) {
                    max = Math.max(max, grid[i][j] + dp[i][j - 1]);
                }
                if (i > 0) {
                    max = Math.max(max, grid[i][j] + dp[i - 1][j]);
                }
                dp[i][j] = max;
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }

    public int nthUglyNumber(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int p2 = 1, p3 = 1, p5 = 1;
        for (int i = 2; i <= n; i++) {
            int num2 = dp[p2] * 2, num3 = dp[p3] * 3, num5 = dp[p5] * 5;
            dp[i] = Math.min(Math.min(num2, num3), num5);
            if (dp[i] == num2) {
                p2++;
            }
            if (dp[i] == num3) {
                p3++;
            }
            if (dp[i] == num5) {
                p5++;
            }
        }
        return dp[n];
    }

    public int maxProfit(int[] prices) {
        int miniPrice = prices[0], value = 0;
        int[] dp = new int[prices.length];
        dp[0] = 0;
        for (int i = 1; i <= prices.length - 1; i++) {
            dp[i] = Math.max(dp[i - 1], prices[i] - miniPrice);
            if (prices[i] < miniPrice) {
                miniPrice = prices[i];
            }
            if (dp[i] > value) {
                value = dp[i];
            }
        }
        return value;
    }

    public int countDigitOne(int n) {
        int[] dp = new int[n + 1];
        int num = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] += oneNum(i, dp);
            num += dp[i];
        }
        return num;
    }

    public int oneNum(int n, int[] dp) {
        int num = 0, s = n;
        while (n > 0) {
            if (n < s) {
                num += dp[n];
                return num;
            }
            if (n % 10 == 1) {
                num++;
            }
            n = n / 10;
        }
        return num;
    }
}
