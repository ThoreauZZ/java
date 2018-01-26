package com.thoreau.algorithm.consistenthash.impl;


import com.thoreau.algorithm.consistenthash.PhysicalNode;

public class RedisNode implements PhysicalNode {
    private String ip;
    private int port;

    public RedisNode(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public String getKey() {
        return ip + ":" + port;
    }

    @Override
    public String toString() {
        return ip + ":" + port;
    }
}
