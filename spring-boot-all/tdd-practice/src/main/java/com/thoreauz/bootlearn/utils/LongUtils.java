package com.thoreauz.bootlearn.utils;

/**
 * 2019/3/24 12:36 AM.
 *
 * @author zhaozhou
 */
public class LongUtils {
    public static boolean isZeroOrNull(Long l) {
        if (l == null) {
            return true;
        }
        return l == 0;
    }
}
