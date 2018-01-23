package com.thoreau.algorithm.trees.rbtree;

/**
 * 2018/1/14 下午10:27.
 * 红黑树数据结构
 *
 * @author zhaozhou
 */
public class RBTreeNode<T extends Comparable> {
    private T value;// 节点值
    private RBTreeNode<T> parent;// 父节点
    private boolean isRed; // 是否红色
    private RBTreeNode<T> left; // 左子树
    private RBTreeNode<T> right; // 右子树

    RBTreeNode(T value) {
        this.value = value;
    }
    public void setValue(T value) {
        this.value = value;
    }

    public void setLeft(RBTreeNode<T> left) {
        this.left = left;
    }

    public void setRed(boolean red) {
        isRed = red;
    }

    public void setRight(RBTreeNode<T> right) {
        this.right = right;
    }

    public void setParent(RBTreeNode<T> parent) {
        this.parent = parent;
    }

    public RBTreeNode<T> getLeft() {
        return left;
    }

    public RBTreeNode<T> getParent() {
        return parent;
    }

    public RBTreeNode<T> getRight() {
        return right;
    }

    public T getValue() {
        return value;
    }

    public boolean isRed() {
        return isRed;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
