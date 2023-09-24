package com.thoreau.algorithm.string;

/**
 * 2023/4/14 21:40.
 *
 * @author zhaozhou
 */
public class BruteForce {
    public static int search(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (text.charAt(i+j) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j == m) {
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        String text = "hello world";
        String pattern = "world";
        int index = search(text, pattern);
        System.out.println(index); // output: 6

        text = "hello world";
        pattern = "java";
        index = search(text, pattern);
        System.out.println(index); // output: -1

        text = "aaaaaa";
        pattern = "aa";
        index = search(text, pattern);
        System.out.println(index); // output: 0

        text = "";
        pattern = "pattern";
        index = search(text, pattern);
        System.out.println(index); // output: -1
    }


}
