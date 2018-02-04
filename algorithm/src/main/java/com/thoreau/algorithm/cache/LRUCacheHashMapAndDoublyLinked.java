package com.thoreau.algorithm.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 2018/2/4 下午7:39.
 *
 * @author zhaozhou
 */
public class LRUCacheHashMapAndDoublyLinked<K, V> implements LruCache<K, V> {
    private class Node {
        K key;
        V value;
        Node prev, next;

        Node(K k, V v) {
            this.key = k;
            this.value = v;
        }
    }

    private int capacity, count;
    private Map<K, Node> map;
    private Node head, tail;

    public LRUCacheHashMapAndDoublyLinked(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        map = new HashMap<>();
        head = new Node(null, null);
        tail = new Node(null, null);
        head.next = tail;
        tail.prev = head;
    }

    private void update(Node node) {
        remove(node);
        add(node);
    }

    private void add(Node node) {
        Node after = head.next;
        head.next = node;
        node.prev = head;
        node.next = after;
        after.prev = node;
    }

    private void remove(Node node) {
        Node before = node.prev, after = node.next;
        before.next = after;
        after.prev = before;
    }

    @Override
    public V get(K k) {
        Node n = map.get(k);
        if (null == n) {
            return null;
        }
        update(n);
        return n.value;
    }

    @Override
    public void put(K key, V value) {
        Node n = map.get(key);
        if (null == n) {
            n = new Node(key, value);
            map.put(key, n);
            add(n);
            ++count;
        } else {
            n.value = value;
            update(n);
        }
        if (count > capacity) {
            Node toDel = tail.prev;
            remove(toDel);
            map.remove(toDel.key);
            --count;
        }
    }
}
