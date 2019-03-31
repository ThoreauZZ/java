package com.thoreau.bootspock.utils;

/**
 * 2019/3/28 11:30 PM.
 *
 * @author zhaozhou
 */
public class MyStringUtils {
    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        }
        return str.trim().length() == 0;
    }
}
