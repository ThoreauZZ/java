package com.thoreau.algorithm.cache;

import java.util.HashMap;
import java.util.LinkedHashSet;

public class LFUCache<K, V> implements Cache<K, V> {
    private HashMap<K, V> vals;
    private HashMap<K, Integer> counts;
    private HashMap<Integer, LinkedHashSet<K>> lists;
    private int cap;
    private int min = -1;

    public LFUCache(int capacity) {
        cap = capacity;
        vals = new HashMap<>();
        counts = new HashMap<>();
        lists = new HashMap<>();
        lists.put(1, new LinkedHashSet<>());
    }

    @Override
    public V get(K key) {
        if (!vals.containsKey(key)) {
            return null;
        }
        // count ++
        int count = counts.get(key);
        counts.put(key, count + 1);

        lists.get(count).remove(key);
        if (count == min && lists.get(count).size() == 0){
            min++;
        }
        if (!lists.containsKey(count + 1)){
            lists.put(count + 1, new LinkedHashSet<>());
        }
        lists.get(count + 1).add(key);
        return vals.get(key);
    }

    @Override
    public void put(K key, V value) {
        if(cap<=0){
            return;
        }
        if(vals.containsKey(key)) {
            vals.put(key, value);
            get(key);
            return;
        }
        if(vals.size() >= cap) {
            K evitKey = lists.get(min).iterator().next();
            lists.get(min).remove(evitKey);
            vals.remove(evitKey);
        }
        vals.put(key, value);
        counts.put(key, 1);
        min = 1;
        lists.get(1).add(key);
    }
}
