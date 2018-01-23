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
        assertEquals(1, parentNode.getValue());
    }

    @Test
    public void findUncelTest() {
        RBTreeNode uncle = rbTree.getUncle(rbTree.getRoot().getLeft());
        assertNull(uncle);
    }

    @Test
    public void addTest() {
        RBTree rbTree = new RBTree<>();
        rbTree.addNode(1);
        rbTree.addNode(2);
        rbTree.addNode(3);
        rbTree.addNode(4);
        rbTree.addNode(5);
        rbTree.addNode(6);
        rbTree.addNode(7);
        rbTree.addNode(8);
        rbTree.addNode(9);

    }

    @Test
    public void removeTest() {
        RBTree rbTree = new RBTree<>();
        rbTree.addNode(1);
        rbTree.remove(1);
        System.out.println("------------------rm root-------------------------");
        rbTree.printTree(rbTree.getRoot());


        rbTree.addNode(1);
        rbTree.addNode(2);
        rbTree.addNode(3);
        rbTree.addNode(4);
        rbTree.addNode(5);
        rbTree.addNode(6);
        rbTree.addNode(7);
        rbTree.addNode(8);
        rbTree.addNode(9);
        System.out.println("--------------------init-----------------------");
        rbTree.printTree(rbTree.getRoot());

        rbTree.remove(6);
        rbTree.printTree(rbTree.getRoot());
        System.out.println("---------------------rm: 6----------------------");

        rbTree.remove(8);
        rbTree.printTree(rbTree.getRoot());
        System.out.println("----------------------rm: 8---------------------");


    }

    @Test
    public void removeTest2() {
        RBTree rbTree = new RBTree<>();
        rbTree.addNode(13);
        rbTree.addNode(2);
        rbTree.addNode(10);
        rbTree.addNode(3);
        rbTree.addNode(1);
        rbTree.addNode(12);
        rbTree.addNode(8);
        rbTree.addNode(20);
        rbTree.addNode(5);
        rbTree.addNode(16);
        rbTree.addNode(19);
        rbTree.addNode(6);
        rbTree.addNode(15);
        rbTree.addNode(14);
        rbTree.addNode(11);
        rbTree.addNode(17);
        rbTree.addNode(7);
        rbTree.addNode(4);
        rbTree.addNode(9);
        rbTree.addNode(18);
        rbTree.printTree(rbTree.getRoot());
        System.out.println("\n-------------------------------------------");
        System.out.println();
        System.out.println();
        System.out.println();

        rbTree.remove(12);
        rbTree.printTree(rbTree.getRoot());
        System.out.println("\n-----------------rm: 12(被删除节点是黑色，儿子为红色)--------------------------\n");


        rbTree.remove(1);
        rbTree.printTree(rbTree.getRoot());
        System.out.println("\n-----------------rm: 1(case6：S是黑色，S的一侧(N同侧)儿子黑色，另一侧儿子为红色)--------------------------\n");

        rbTree.remove(9);
        rbTree.printTree(rbTree.getRoot());
        System.out.println("\n-----------------rm: 9(红色直接删除)--------------------------\n");

        rbTree.remove(10);
        rbTree.printTree(rbTree.getRoot());
        System.out.println("\n-----------------rm: 10(case4：P为红色，S和儿子都不是红色)--------------------------\n");
    }
}
