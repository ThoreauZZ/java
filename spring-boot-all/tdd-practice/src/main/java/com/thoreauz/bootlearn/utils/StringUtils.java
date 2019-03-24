package com.thoreauz.bootlearn.utils;

/**
 * 2019/3/23 10:21 PM.
 *
 * @author zhaozhou
 */
public class StringUtils {
    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        }
        return str.trim().length() == 0;
    }

    public static boolean hasLength(String str) {
        return (str != null && !str.isEmpty());
    }
}
