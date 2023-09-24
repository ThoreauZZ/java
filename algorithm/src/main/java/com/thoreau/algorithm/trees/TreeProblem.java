package com.thoreau.algorithm.trees;

import java.util.LinkedList;
import java.util.List;

/**
 * 2023/4/25 22:59.
 *
 * @author zhaozhou
 */
public class TreeProblem {
    /**
     * 给定一棵二叉搜索树，请找出其中第 k 大的节点的值。
     * <p>
     * 输入: root = [3,1,4,null,2], k = 1
     * 3
     * / \
     * 1   4
     * \
     * 2
     * 输出: 4
     * <p>
     * 输入: root = [5,3,6,2,4,null,null,1], k = 3
     * 5
     * / \
     * 3   6
     * / \
     * 2   4
     * /
     * 1
     * 输出: 4
     * <p>
     * <p>
     * ===解题思路====
     * 1. 中序遍历后，就简单了；
     * <p>
     * 方法二：递归
     */
    TreeNode res;
    int k = 0;

    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        getKNode(root);
        return res.val;
    }

    public int getKNode(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int r = getKNode(root.right);
        int l = getKNode(root.left);
        int i = l + r + 1;
        if (i == k) {
            res = root;
        }
        return i;
    }

    List<List<Integer>> list = new LinkedList<>();
    LinkedList<Integer> queue = new LinkedList<>();

    /**
     * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，
     * 找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
     *
     * @param root
     * @param target
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        pathSum2(root, target);
        return list;
    }

    private void pathSum2(TreeNode node, int target) {
        if (node == null) {
            return;
        }
        target = target - node.val;
        int value = target;
        queue.addLast(node.val);
        if (target == 0 && node.left == null && node.right == null) {
            list.add(new LinkedList(queue));
        }
        pathSum2(node.left, target);
        target = value;
        pathSum2(node.right, target);
        queue.removeLast();
    }
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

}
