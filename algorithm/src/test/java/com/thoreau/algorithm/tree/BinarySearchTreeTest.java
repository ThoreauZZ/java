package com.thoreau.algorithm.tree;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        Assert.assertEquals("5 1 3 2 4 9 6 8 7 10 ", tree.preOrder());
    }
    @Test
    public void inOrderTest() {
        Assert.assertEquals("1 2 3 4 5 6 7 8 9 10 ", tree.inOrder());
    }
    @Test
    public void postOrderTest() {
        Assert.assertEquals("2 4 3 1 7 8 6 10 9 5 ", tree.postOrder());
    }
    @Test
    public void levelOrderTest() {
        Assert.assertEquals("5 1 9 3 6 10 2 4 8 7 ", tree.levelOrder());
    }
    @Test
    public void findMinTest() {
        Assert.assertEquals(1, tree.findMin());
    }
    @Test
    public void findMaxTest() {
        Assert.assertEquals(10, tree.findMax());
    }
    @Test
    public void sizeTest() {
        Assert.assertEquals(10, tree.findMax());
    }
    @Test
    public void heightTest() {
        Assert.assertEquals(5, tree.height());
    }
    @Test
    public void removeTest() {
        tree.remove(1);
        Assert.assertEquals("5 3 2 4 9 6 8 7 10 ", tree.preOrder());
    }
}
