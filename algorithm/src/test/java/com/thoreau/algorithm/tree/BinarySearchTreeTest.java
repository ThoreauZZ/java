package com.thoreau.algorithm.tree;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 17/10/15 下午8:12.
 *
 * @author zhaozhou
 */
public class BinarySearchTreeTest {

    private BinarySearchTree tree = new BinarySearchTree();

    @Before
    public void init() {
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
    @Test
    public void preOrderTest() {
        assertEquals("5 1 3 2 4 9 6 8 7 10 ", tree.preOrder());
    }
    @Test
    public void inOrderTest() {
        assertEquals("1 2 3 4 5 6 7 8 9 10 ", tree.inOrder());
    }
    @Test
    public void postOrderTest() {
        assertEquals("2 4 3 1 7 8 6 10 9 5 ", tree.postOrder());
    }
    @Test
    public void levelOrderTest() {
        assertEquals("5 1 9 3 6 10 2 4 8 7 ", tree.levelOrder());
    }
    @Test
    public void findMinTest() {
        assertEquals(1, tree.findMin());
    }
    @Test
    public void findMaxTest() {
        assertEquals(10, tree.findMax());
    }
    @Test
    public void sizeTest() {
        assertEquals(10, tree.findMax());
    }
    @Test
    public void heightTest() {
        assertEquals(5, tree.height());
    }
    @Test
    public void removeTest() {
        tree.remove(1);
        assertEquals("5 3 2 4 9 6 8 7 10 ", tree.preOrder());
    }
    @Test
    public void countLeavesTest() {
        assertEquals(4,tree.countLeaves());
    }
    @Test
    public void kSizeTest() {
        assertEquals(3,tree.kSize(4));
    }
    @Test
    public void compareTreeTest() {
        Assert.assertTrue(tree.compareTree(tree.getRoot(),tree.getRoot()));
    }

    @Test
    public void mirrorTest() {
        tree.mirror();
        System.out.println(tree.inOrder());
        assertEquals("10 9 8 7 6 5 4 3 2 1 ",tree.inOrder());
    }
    @Test
    public void findLCATest() {
        tree.mirror();
        assertEquals(5,tree.findLCA(3,2));
    }
}
