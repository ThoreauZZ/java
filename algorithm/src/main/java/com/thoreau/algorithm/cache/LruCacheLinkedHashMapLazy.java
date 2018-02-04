package com.thoreau.algorithm.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 2018/2/4 下午7:13.
 *
 * @author zhaozhou
 */
public class LruCacheLinkedHashMapLazy<K, V> implements LruCache<K,V> {
    private LinkedHashMap<K, V> map;
    private final int CAPACITY;

    public LruCacheLinkedHashMapLazy(int capacity) {
        CAPACITY = capacity;
        map = new LinkedHashMap<K, V>(capacity, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > CAPACITY;
            }
        };
    }

    @Override
    public V get(K key) {
        return map.getOrDefault(key, null);
    }

    @Override
    public void put(K key, V value) {
        map.put(key, value);
    }
}
