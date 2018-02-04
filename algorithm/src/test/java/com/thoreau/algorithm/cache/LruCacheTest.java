package com.thoreau.algorithm.cache;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * 2018/1/26 下午11:38.
 *
 * @author zhaozhou
 */
public class LruCacheTest {
    @Test
    public void testCache() {
        final int maxSize = 10;
        LruCacheLinkedHashMap<Integer, Integer> lruCacheLinkeHashMap = new LruCacheLinkedHashMap<>(maxSize);
        for (int i = 0; i < maxSize; i++) {
            lruCacheLinkeHashMap.put(i, i);
        }
        System.out.println("当前数据：" + lruCacheLinkeHashMap.toString());
        System.out.println("key为6的数据" + lruCacheLinkeHashMap.get(6));
        System.out.println("访问key为6的数据后：" + lruCacheLinkeHashMap.toString());
        lruCacheLinkeHashMap.put(11, 11);
        System.out.println("插入value为11的数据后：" + lruCacheLinkeHashMap.toString());
    }

    @Test
    public void testLruCacheLinkedHashMapLazy() {
        LruCache<Integer, Integer> lruCache = new LruCacheLinkedHashMapLazy<>(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        assertEquals(new Integer(1), lruCache.get(1));
        lruCache.put(3, 3);
        assertNull(lruCache.get(2));
        lruCache.put(4,4);
        assertNull(lruCache.get(1));
        assertEquals(new Integer(3),lruCache.get(3));
    }
    @Test
    public void testLRUCacheHashMapAndDoublyLinked() {
        LruCache<Integer, Integer> lruCache = new LRUCacheHashMapAndDoublyLinked<>(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        assertEquals(new Integer(1), lruCache.get(1));
        lruCache.put(3, 3);
        assertNull(lruCache.get(2));
        lruCache.put(4,4);
        assertNull(lruCache.get(1));
        assertEquals(new Integer(3),lruCache.get(3));
    }
}
