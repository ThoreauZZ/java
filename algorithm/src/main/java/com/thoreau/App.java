package com.thoreau;

/**
 * Hello world!
 */

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * [
 * [1,   3,  5,  7],
 * [10, 11, 16, 20],
 * [23, 30, 34, 50]
 * ]
 */
public class App {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 16; i++) {
            map.put("key" + i, "value" + i);
        }
    Map<String, String> concurrentHashMap = new ConcurrentHashMap<>();
    }
}
