package com.thoreau.algorithm.consistenthash;

import lombok.Getter;

/**
 * @author zhaozhou
 */
@Getter
public class VirtualNode {
    /**
     * 虚拟节点序号
     */
    private int replicaNumber;
    /**
     * 对应的物理节点
     */
    private PhysicalNode parent;

    public VirtualNode(PhysicalNode parent, int replicaNumber) {
        this.replicaNumber = replicaNumber;
        this.parent = parent;
    }
    /**
     * 使用物理节点的"key#12"作为虚拟节点的key
     *
     * @return String
     */
    public String getKey() {
        return parent.getKey() + "#" + replicaNumber;
    }
}
