package com.thoreau.algorithm.cache;

public interface Cache<K, V> {
    V get(K k);

    void put(K key, V value);
}
