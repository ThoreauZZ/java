package com.thoreau.algorithm.tree;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author thoreau
 */
public class BinarySearchTree<T extends Comparable> implements Tree<T> {
    private BinaryNode<T> root;

    @Override
    public int size() {
        return size(root);
    }

    private int size(BinaryNode<T> node) {
        if (node == null) {
            return 0;
        } else {
            return size(node.left) + 1 + size(node.right);
        }

    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(BinaryNode<T> node) {
        if (node == null) {
            return 0;
        }else {
            int l = height(node.left);
            int r = height(node.right);
            return (l > r) ? l+1 : r+1;
        }
    }

    @Override
    public String preOrder() {
        return preOrder(root);
    }

    private String preOrder(BinaryNode<T> subTree) {
        StringBuilder sb = new StringBuilder();
        if (subTree != null) {
            sb.append(subTree.data).append(" ");
            sb.append(preOrder(subTree.left));
            sb.append(preOrder(subTree.right));
        }
        return sb.toString();
    }

    @Override
    public String inOrder() {
        return inOrder(root);
    }

    private String inOrder(BinaryNode<T> subTree) {
        StringBuilder sb = new StringBuilder();
        if (subTree != null) {
            sb.append(inOrder(subTree.left));
            sb.append(subTree.data).append(" ");
            sb.append(inOrder(subTree.right));
        }
        return sb.toString();
    }

    @Override
    public String postOrder() {
        return postOrder(root);
    }

    private String postOrder(BinaryNode<T> subTree) {
        StringBuilder sb = new StringBuilder();
        if (subTree != null) {
            sb.append(postOrder(subTree.left));
            sb.append(postOrder(subTree.right));
            sb.append(subTree.data).append(" ");
        }
        return sb.toString();
    }

    @Override
    public String levelOrder() {
        LinkedBlockingQueue<BinaryNode<T>> queue = new LinkedBlockingQueue();
        StringBuilder sb = new StringBuilder();
        BinaryNode<T> tree = root;
        while (tree != null) {
            sb.append(tree.data).append(" ");
            if (tree.left != null) {
                queue.add(tree.left);
            }
            if (tree.right != null) {
                queue.add(tree.right);
            }
            tree = queue.poll();
        }
        return sb.toString();
    }

    @Override
    public void insert(T data) {
        if (data == null) {
            throw new RuntimeException("data is null");
        }
        root = insert(data, root);
    }

    private BinaryNode<T> insert(T data, BinaryNode<T> node) {
        if (node == null) {//节点为空，则赋值
            node = new BinaryNode<T>(data);
        }
        int result = data.compareTo(node.data);
        if (result < 0) {// 左
            node.left = insert(data, node.left);
        } else if (result > 0) {// 右
            node.right = insert(data, node.right);
        }
        return node;
    }

    @Override
    public void remove(T data) {
        remove(data, root);
    }

    private BinaryNode<T> remove(T data, BinaryNode<T> node) {
        if (node == null) {//结束条件: 未找到
            return node;
        }
        int result = data.compareTo(node.data);
        if (result < 0) {
            node.left = remove(data, node.left);
        } else if (result > 0) {
            node.right = remove(data, node.right);
        } else if (node.left != null && node.right != null) {// 找到对应值
            node.data = findMin(node.right).data;// 找到右子树最小值替换
            node.right = remove(node.data, node.right);
        } else {// 只有一个子
            node =  (node.left != null) ? node.left : node.right;
        }
        return node;
    }


    @Override
    public T findMin() {
        if (root == null) {
            throw new RuntimeException("root is null");
        }
        return findMin(root).data;
    }

    /**
     * 根据输入节点作为根节点查找树中最小节点
     *
     * @param node
     * @return
     */
    private BinaryNode<T> findMin(BinaryNode<T> node) {
        if (node == null) {
            throw new RuntimeException("Node is null");
        }
        if (node.left == null) {// (1). 左节点空，则最小
            return node;
        }
        return findMin(node.left);//(2). 不空，则递归
    }


    @Override
    public T findMax() {
        return findMax(root).data;
    }

    private BinaryNode<T> findMax(BinaryNode<T> node) {
        if (node == null) {
            throw new RuntimeException("Node is null");
        }
        if (node.right == null) {
            return node;
        }
        return findMax(node.right);
    }
}
