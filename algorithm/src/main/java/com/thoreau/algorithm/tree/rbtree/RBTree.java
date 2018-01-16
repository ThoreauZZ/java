package com.thoreau.algorithm.tree.rbtree;

/**
 * 2018/1/15 下午10:58.
 *
 * @author zhaozhou
 */
public class RBTree<T extends Comparable<T>> {
    private RBTreeNode<T> root;

    RBTree(RBTreeNode<T> node) {
        this.root = node;
    }


    public T addNode(T value) {
        RBTreeNode<T> t = new RBTreeNode<T>(value);
        return addNode(t);
    }

    // insert
    private T addNode(RBTreeNode<T> node) {

        node.setLeft(null);
        node.setRight(null);
        node.setRed(true);// 新插入的节点为红色

        if (root == null) {
            // 新插入节点为跟节点
            root = node;
            root.setRed(false);
            return root.getValue();
        }
        // 找到要插入的父节点
        RBTreeNode<T> parentNode = findParentNode(node);
        int result = parentNode.getValue().compareTo(node.getValue());
        if (result == 0) {
            return node.getValue();
        }
        if (result > 0) {
            parentNode.setLeft(node);
        } else {
            parentNode.setRight(node);
        }
        node.setParent(parentNode);
        if (parentNode.isRed()) {
            fixAdd(node);
        }
        return null;

    }

    private void fixAdd(RBTreeNode<T> node) {
//        if(node.getParent().getParent().)
    }

    public RBTreeNode<T> findParentNode(RBTreeNode<T> node) {
        RBTreeNode<T> parent = root;
        RBTreeNode<T> child = root;
        while (child != null) {
            int result = child.getValue().compareTo(node.getValue());
            if (result == 0) {
                // 和其中一只节点相同
                return child;
            }
            parent = child;
            if (result > 0) {
                child = child.getLeft();
            } else {
                child = child.getRight();
            }
        }
        return parent;
    }

    /**
     * set parent
     *
     * @param node   node
     * @param parent parent
     */
    private void setParent(RBTreeNode<T> node, RBTreeNode<T> parent) {
        if (node != null) {
            node.setParent(parent);
            if (parent == root) {
                node.setParent(null);
            }
        }
    }
}
