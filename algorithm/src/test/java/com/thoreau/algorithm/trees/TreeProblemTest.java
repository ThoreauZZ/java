package com.thoreau.algorithm.trees;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

/**
 * 2023/4/25 23:00.
 *
 * @author zhaozhou
 */
public class TreeProblemTest {
    TreeProblem sl = new TreeProblem();
    @Test
    public void kthLargestTest() {


    }
    @Test
    public void maxDepthTest() {
        TreeNode treeNode = buildTree(new Integer[]{3,9,20,null,null,15,7});
        assertEquals(3, sl.maxDepth(treeNode));
        assertEquals(2, sl.maxDepth(buildTree(new Integer[]{1,2})));

    }
    @Test
    public void pathSumTest() {
        TreeNode treeNode = buildTree(new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1});
        System.out.println(sl.pathSum(treeNode, 22));


    }
    public TreeNode buildTree(Integer[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        int i = 0;
        TreeNode root = new TreeNode(nums[i++]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (i < nums.length && nums[i] != null) {
                node.left = new TreeNode(nums[i]);
                queue.offer(node.left);
            }
            i++;
            if (i < nums.length && nums[i] != null) {
                node.right = new TreeNode(nums[i]);
                queue.offer(node.right);
            }
            i++;
        }

        return root;
    }

}