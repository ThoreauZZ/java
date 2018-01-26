package com.thoreau.algorithm.consistenthash;

import com.thoreau.algorithm.consistenthash.impl.MD5Hash;
import com.thoreau.algorithm.consistenthash.impl.RedisNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ConsistentHashTest {
    @Test
    public void testNode() {
        List<PhysicalNode> redisNodes = new ArrayList<>();
        IHash hash = new MD5Hash();
        redisNodes.add(new RedisNode("192.168.99.100", 8080));
        redisNodes.add(new RedisNode("192.168.99.101", 8080));
        redisNodes.add(new RedisNode("192.168.99.102", 8080));
        redisNodes.add(new RedisNode("192.168.99.103", 8080));
        redisNodes.add(new RedisNode("192.168.99.104", 8080));
        ConsistentHash consistentHash = new ConsistentHash(redisNodes);
        String key1 = "hello";
        String key2 = "world";
        String key3 = "name";
        String key4 = "age";
        log.info("key:{}, hash:{}, node:{}", key1, hash.getHash(key1), consistentHash.getNode(key1));
        log.info("key:{}, hash:{}, node:{}", key2, hash.getHash(key2), consistentHash.getNode(key2));
        log.info("key:{}, hash:{}, node:{}", key3, hash.getHash(key3), consistentHash.getNode(key3));
        log.info("key:{}, hash:{}, node:{}", key4, hash.getHash(key4), consistentHash.getNode(key4));
    }
    Map<String, AtomicInteger> count = new HashMap<>();
    @Test
    public void testBalance() {
        List<PhysicalNode> redisNodes = new ArrayList<>();
        IHash hash = new MD5Hash();
        redisNodes.add(new RedisNode("192.168.99.100", 8080));
        redisNodes.add(new RedisNode("192.168.99.101", 8080));
        redisNodes.add(new RedisNode("192.168.99.102", 8080));
        redisNodes.add(new RedisNode("192.168.99.103", 8080));
        ConsistentHash consistentHash = new ConsistentHash(redisNodes);
        for (int i = 0; i < 1000000; i++) {
            addCount(consistentHash.getNode("key" + i));
        }
        System.out.println(count);
    }
    private void addCount(PhysicalNode node) {
        if (count.get(node.getKey()) == null) {
            count.put(node.getKey(), new AtomicInteger(1));
        } else {
            count.get(node.getKey()).addAndGet(1);
        }
    }
}