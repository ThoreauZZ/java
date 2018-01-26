package com.thoreau.algorithm.consistenthash;

/**
 * 物理节点接口
 */
public interface PhysicalNode {
    /**
     * 返回用于计算hash的key
     */
    String getKey();
}
