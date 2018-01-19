package com.thoreau.algorithm.tree.rbtree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * 2018/1/15 下午11:28.
 *
 * @author zhaozhou
 */
public class RBTreeTest {
    private RBTree rbTree;

   @Before
   public void setUp() {
       RBTreeNode root = new RBTreeNode(6);
       root.setRed(false);

       RBTreeNode left = new RBTreeNode(1);
       left.setParent(root);
       left.setRed(true);

       RBTreeNode right = new RBTreeNode(8);
       right.setParent(root);
       right.setRed(true);

       root.setLeft(left);
       root.setRight(right);
       rbTree = new RBTree(root);
   }

    @Test
    public void findParentNodeTest() {
        RBTreeNode parentNode = rbTree.findParentNode(new RBTreeNode(3));
        assertEquals(1,parentNode.getValue());
    }
    @Test
    public void findUncelTest() {
        RBTreeNode uncle = rbTree.getUncle(rbTree.getRoot().getLeft());
        assertNull(uncle);
    }

}
