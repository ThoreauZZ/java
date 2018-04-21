package com.thoreauz.bootlearn.testng;

/**
 * 2018/4/20 下午11:50.
 *
 * @author zhaozhou
 */
public class StringUtils {
    public static boolean isBlank(String str) {
        return str == null || "".equals(str);
    }

    public static String trim(String s) {
        return s.trim();
    }
}
