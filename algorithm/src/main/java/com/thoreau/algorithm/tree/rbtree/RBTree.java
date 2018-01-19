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

    public RBTreeNode<T> getRoot() {
        return root;
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

    /**
     * @param node node
     */
    private void fixAdd(RBTreeNode<T> node) {
        RBTreeNode<T> parent = node.getParent();
        RBTreeNode<T> uncle = getUncle(node);
        while (parent != null && parent.isRed()) {
            if (uncle == null) {
                RBTreeNode<T> ancestor = parent.getParent();
                if (ancestor.getLeft() == parent) {
                    boolean isRight = node == parent.getRight();
                    if (isRight) {

                    } else {
                        // 一条斜线，右旋
                        rotateRight(ancestor);
                    }
                } else {

                }
            } else if (uncle.isRed()) {
                // 1. 叔叔不为空
                uncle.setRed(false);
                parent.setRed(false);
                parent.getParent().setRed(true);

                RBTreeNode<T> newNode = parent.getParent();
                parent = newNode.getParent();
            }
        }
        getRoot().setRed(false);
        getRoot().setParent(null);
    }

    /**
     * 右旋
     *
     * @param node node
     */
    private void rotateRight(RBTreeNode<T> node) {
        RBTreeNode<T> left = node.getLeft();
        if (left == null) {
            throw new java.lang.IllegalStateException("left node is null");
        }
        RBTreeNode<T> parent = node.getParent();
        node.setLeft(left.getRight());
        setParent(left.getRight(), node);
        left.setRight(node);
        setParent(node, left);

        if (parent == null) {
            root.setLeft(left);
            setParent(left, null);
        } else {
            if (parent.getLeft() == node) {
                parent.setLeft(left);
            } else {
                parent.setRight(left);
            }
            setParent(left, parent);
        }
    }

    /**
     * 左旋
     * @param node node
     */
    private void rotateLeft(RBTreeNode<T> node) {
        // 把待替换节(n)的右节点(r)替换它
        RBTreeNode<T> replaceNode = node.getRight();
        node.setRight(replaceNode.getLeft());

        // r.l = n
        replaceNode.setLeft(node);
        // n.p = r
        node.setParent(replaceNode);

        if (replaceNode.getLeft() != null) {
            replaceNode.getLeft().setParent(node);
        }
        // r.p = n.p
        replaceNode.setParent(node.getParent());
        if (node.getParent() == null) {
            // p = null, 就是根节点
            root = replaceNode;
        }else {
            // p 重置子节点
            if (node.getParent().getLeft() == node) {
                node.getParent().setLeft(replaceNode);
            }else {
                node.getParent().setRight(replaceNode);
            }
        }

    }

    /**
     * 寻找父节点:
     * 跟二叉查找树一样，比对节点值大小，找到要插入的父节点
     *
     * @param node node
     * @return RBTreeNode node
     */
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
     * 找到叔叔节点
     *
     * @param node node
     * @return uncle
     */
    public RBTreeNode<T> getUncle(RBTreeNode<T> node) {
        RBTreeNode<T> parent = node.getParent();
        RBTreeNode<T> grandfather = parent.getParent();
        if (grandfather == null) {
            return null;
        }
        if (parent == grandfather.getLeft()) {
            return grandfather.getRight();
        } else {
            return grandfather.getLeft();
        }
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
