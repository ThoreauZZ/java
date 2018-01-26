package com.thoreau.algorithm.consistenthash;

/**
 * hash算法接口
 */
public interface IHash {
    /**
     * 根据字符串key，获取long型hash值
     *
     * @param key 用户计算hash的key
     * @return hash值
     */
    long getHash(String key);
}
