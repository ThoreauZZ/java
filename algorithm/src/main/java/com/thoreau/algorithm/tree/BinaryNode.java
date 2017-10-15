package com.thoreau.algorithm.tree;

/**
 * @author zhaozhou
 */
public class BinaryNode<T extends Comparable> {
    public T data;// 节点值
    public BinaryNode<T> left;//左子树
    public BinaryNode<T> right;//右子树

    /**
     * data和左右子树构造节点
     *
     * @param data  节点值
     * @param left  左子树
     * @param right 右子树
     */
    public BinaryNode(T data, BinaryNode<T> left, BinaryNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    /**
     * data构造节点
     *
     * @param data 节点值
     */
    public BinaryNode(T data) {
        this(data, null, null);
    }
}
