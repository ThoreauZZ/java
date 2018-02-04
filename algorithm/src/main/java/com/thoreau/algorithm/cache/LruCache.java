package com.thoreau.algorithm.cache;

/**
 * 2018/2/4 下午7:15.
 *
 * @author zhaozhou
 */
public interface LruCache<K,V> {
    V get(K k);

    void put(K key, V value);

}
