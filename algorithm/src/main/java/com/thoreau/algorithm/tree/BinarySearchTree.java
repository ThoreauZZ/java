package com.thoreau.algorithm.tree;

public class BinarySearchTree<T extends Comparable> implements Tree<T> {
    private BinaryNode<T> root;

    public int size() {
        return 0;
    }

    public int height() {
        return 0;
    }

    public String preOrder() {
        return null;
    }

    public String inOrder() {
        return null;
    }

    public String postOrder() {
        return null;
    }

    public String levelOrder() {
        return null;
    }

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
            node.data = findMin(node.right).data;

        } else if (node.left == null && node.right == null) {
            node = null;
        } else {// 只有一个子
            return node.left == null ? node.left : node.right;
        }
    }

}

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


    public T findMax() {
        return null;
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(5);
        tree.insert(1);
        tree.insert(9);
        tree.insert(3);
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        tree.insert(10);
        tree.insert(8);
        tree.insert(7);

    }
}
