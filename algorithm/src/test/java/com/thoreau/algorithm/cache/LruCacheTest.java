package com.thoreau.algorithm.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import org.junit.Test;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

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
        lruCache.put(4, 4);
        assertNull(lruCache.get(1));
        assertEquals(new Integer(3), lruCache.get(3));
    }

    @Test
    public void testLRUCacheHashMapAndDoublyLinked() {
        LruCache<Integer, Integer> lruCache = new LRUCacheHashMapAndDoublyLinked<>(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        assertEquals(new Integer(1), lruCache.get(1));
        lruCache.put(3, 3);
        assertNull(lruCache.get(2));
        lruCache.put(4, 4);
        assertNull(lruCache.get(1));
        assertEquals(new Integer(3), lruCache.get(3));
    }

    @Test
    public void testGuava() throws ExecutionException, InterruptedException {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(2, TimeUnit.SECONDS)
                .removalListener((RemovalListener<String, String>) removalNotification -> System.out.println("Remove key " + removalNotification.getKey() + " ..."))
                .build(new CacheLoader<String, String>() {
                    public String load(String key) {
                        return key + "-value";
                    }
                });
        for (int i = 0; i < 20; i++) {
            cache.put("key" + i, "vaue" + i);
        }
        System.out.println(cache.get("key19"));
        System.out.println("--------------");
        Thread.sleep(3000);
        System.out.println(cache.get("key19"));
    }

    @Test
    public void testReferenceQueue() {
        ReferenceQueue<byte[]> referenceQueue = new ReferenceQueue<>();
        Object value = new Object();
        Map<Object, Object> map = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            byte[] bytes = new byte[1048576];
            WeakReference<byte[]> weakReference = new WeakReference<byte[]>(bytes, referenceQueue);
            map.put(weakReference, value);
        }
        Thread thread = new Thread(() -> {
            try {
                int cnt = 0;
                WeakReference<byte[]> k;
                while ((k = (WeakReference) referenceQueue.remove()) != null) {
                    System.out.println((cnt++) + "回收了:" + k);
                }
            } catch (InterruptedException e) {
            } finally {
                System.out.println("结束循环");
            }
        });
        thread.setDaemon(true);
        thread.start();
        System.out.println("map.size->" + map.size());
    }
}
