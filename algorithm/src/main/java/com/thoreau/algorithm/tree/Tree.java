package com.thoreau.algorithm.tree;

public interface Tree<T extends Comparable> {

    /**
     * 二叉树的结点个数
     */
    int size();

    /**
     * 叶子数
     */
    int countLeaves();

    /**
     * 第k层节点数
     */
    int kSize(int k);

    /**
     * 二叉树的高度
     */
    int height();

    /**
     * 先根次序遍历(根-左-右)
     */
    String preOrder();

    /**
     * 中根次序遍历（左-中-右）
     */
    String inOrder();

    /**
     * 后根次序遍历(左-右-中)
     */
    String postOrder();

    /**
     * 层次遍历（从左到右一层层遍历）
     */
    String levelOrder();

    /**
     * 插入数据到指定节点
     *
     * @return
     */
    void insert(T data);

    /**
     * 删除节点数据
     */
    void remove(T data);

    /**
     * 找到最小节点
     */
    T findMin();

    /**
     * 找到最大节点
     */
    T findMax();

    boolean compareTree(BinaryNode<T> node1, BinaryNode<T> node2);

}
