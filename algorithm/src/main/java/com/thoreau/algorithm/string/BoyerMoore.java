package com.thoreau.algorithm.string;

/**
 * 2023/4/14 23:01.
 *
 * @author zhaozhou
 */
public class BoyerMoore {
    public static int bmBadChar(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int[] bc = new int[256]; // 记录字符最后出现的位置
        for (int i = 0; i < 256; i++) {
            bc[i] = -1;
        }
        for (int i = 0; i < m; i++) {
            bc[pattern.charAt(i)] = i;
        }
        int i = 0; // i 表示 text 中比对到的第一个字符的下标
        while (i <= n - m) {
            int j;
            for (j = m - 1; j >= 0; j--) {
                if (text.charAt(i+j) != pattern.charAt(j)) {
                    break; // 发现坏字符，跳出循环
                }
            }
            if (j < 0) {
                return i; // 匹配成功
            }
            int x = j - bc[text.charAt(i+j)]; // 计算坏字符规则下移的位数
            i += x > 0 ? x : 1; // 移动i的位置
        }
        return -1; // 匹配失败
    }
    public static void main(String[] args) {
        String text = "HERE IS A SIMPLE EXAMPLE";
        String pattern = "EXAMPLE";
        int index = bmBadChar(text, pattern);
        System.out.println("BM bad character algorithm: pattern found at index " + index);
    }

}
