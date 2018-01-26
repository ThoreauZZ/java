package com.thoreau.algorithm.consistenthash;

import com.thoreau.algorithm.consistenthash.impl.MD5Hash;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author zhaozhou
 */
@Slf4j
public class ConsistentHash {
    /**
     * hash 轮使用treeMap存储：
     * <p>
     * 1. treeMap基于红黑树实现，查找时间复杂度O(logN)
     * 2. 提供tailMap(K fromKey)，返回fromKey之后的map。正好和hash环顺时针查找切和
     */
    private SortedMap<Long, VirtualNode> hashWheel = new TreeMap<>();
    /**
     * 默认虚拟节点数
     */
    private final static int DEFAULT_VNODE_SIZE = 150;
    /**
     * md5实现hash
     */
    private IHash hashfunc = new MD5Hash();

    /**
     * 初始化节点
     *
     * @param servers
     */
    public ConsistentHash(Collection<PhysicalNode> servers) {
        for (PhysicalNode server : servers) {
            addNode(server);
        }
    }

    /**
     * 添加节点
     *
     * @param physicalNode 节点
     */
    public void addNode(PhysicalNode physicalNode) {
        // 获取虚拟节点序号
        int virtualNumber = getVirtualNumber(physicalNode.getKey());
        for (int i = 0; i < DEFAULT_VNODE_SIZE; i++) {
            VirtualNode virtualNode = new VirtualNode(physicalNode, virtualNumber + i);
            // 对虚拟节点key计算hash值
            long hash = hashfunc.getHash(virtualNode.getKey());
            hashWheel.put(hash, virtualNode);
        }
        log.info("add node[{}],hash:[{}]", physicalNode.getKey());
    }

    /**
     * 获取节点
     *
     * @param key key
     * @return 节点信息
     */
    public PhysicalNode getNode(String key) {
        long hash = hashfunc.getHash(key);
        SortedMap<Long, VirtualNode> collect = hashWheel.tailMap(hash);
        if (collect.size() == 0) {
            return hashWheel.get(hashWheel.firstKey()).getParent();
        }
        return hashWheel.get(collect.firstKey()).getParent();
    }

    /**
     * parentKey#number
     *
     * @param nodeKey nodeKey
     * @return number
     */
    private int getVirtualNumber(String nodeKey) {
        int num = 0;
        for (VirtualNode node : hashWheel.values()) {
            if (node.getKey().equals(nodeKey)) {
                num++;
            }
        }
        return num;
    }
}
