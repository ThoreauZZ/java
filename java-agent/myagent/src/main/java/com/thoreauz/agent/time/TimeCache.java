package com.thoreauz.agent.time;

import java.util.HashMap;
import java.util.Map;

/**
 * 2019/1/10 10:43 PM.
 *
 * @author zhaozhou
 */
public class TimeCache {
    public static Map<String, Long> startTimeMap = new HashMap<>();
    public static Map<String, Long> endTimeMap = new HashMap<>();
    public static void setStartTime(String methodName, long time) {
        startTimeMap.put(methodName, time);
    }

    public static void setEndTime(String methodName, long time) {
        endTimeMap.put(methodName, time);
    }

    public static String getCostTime(String methodName) {
        long start = startTimeMap.get(methodName);
        long end = endTimeMap.get(methodName);
        return methodName + "[" + (end - start) + " ms]";
    }
}
