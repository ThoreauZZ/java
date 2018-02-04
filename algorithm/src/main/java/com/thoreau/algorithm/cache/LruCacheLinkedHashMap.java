package com.thoreau.algorithm.cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 2018/1/26 下午11:29.
 *
 * @author zhaozhou
 */
public class LruCacheLinkedHashMap<K, V> implements LruCache<K,V>{
    private LinkedHashMap<K, V> map;
    //最大缓存容量
    private int maxSize;
    //当前容量
    private AtomicInteger size;

    public LruCacheLinkedHashMap(int maxSize) {
        this.maxSize = maxSize;
        size = new AtomicInteger(0);
        map = new LinkedHashMap<>(0, 0.75f, true);
    }

    /**
     * 插入一条数据，更新当前容量大小，并检测是否已超出容量
     *
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        size.addAndGet(1);
        map.put(key, value);
        trimSize(maxSize);
    }

    public V get(K key) {
        V v;
        synchronized (this) {
            v = map.get(key);
        }
        return v;
    }

    @Override
    public String toString() {
        return "size:" + size + ";maxSize:" + maxSize + " " + map.toString();
    }

    /**
     * 检测当前容量是否已经超过最大容量，如果超过就开始清除数据，知道size小于maxSize为止。
     *
     * @param maxSize
     */
    private void trimSize(int maxSize) {
        while (true) {
            K key;
            synchronized (this) {
                if (size.get() < 0 || (map.isEmpty() && size.get() != 0)) {
                    throw new IllegalStateException("map size wrong ...");
                }
                if (size.get() <= maxSize || map.isEmpty()) {
                    break;
                }
                Map.Entry<K, V> toEvict = map.entrySet().iterator().next();
                key = toEvict.getKey();
                map.remove(key);
                size.addAndGet(-1);
            }
        }
    }
}
