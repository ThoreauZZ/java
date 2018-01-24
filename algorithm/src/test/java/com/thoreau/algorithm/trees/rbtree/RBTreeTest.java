package com.thoreau.algorithm.trees.rbtree;

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
        String expect = "4(B) \n" +
                "2(R 4/l) 6(R 4/r) \n" +
                "1(B 2/l) 3(B 2/r) 5(B 6/l) 8(B 6/r) \n" +
                "7(R 8/l) 9(R 8/r) ";
        assertEquals(expect,rbTree.printTree());
    }

    @Test
    public void removeTest() {
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
        String expect = "10(B) \n" +
                "5(B 10/l) 15(B 10/r) \n" +
                "2(R 5/l) 7(R 5/r) 13(R 15/l) 19(R 15/r) \n" +
                "1(B 2/l) 3(B 2/r) 6(B 7/l) 8(B 7/r) 12(B 13/l) 14(B 13/r) 17(B 19/l) 20(B 19/r) \n" +
                "4(R 3/r) 9(R 8/r) 11(R 12/l) 16(R 17/l) 18(R 17/r) ";
        assertEquals(expect,rbTree.printTree());

        System.out.println("rm: 13(case6：S是黑色，S的另一侧儿子为红色))");
        rbTree.remove(13);
        expect = "10(B) \n" +
                "5(B 10/l) 15(B 10/r) \n" +
                "2(R 5/l) 7(R 5/r) 12(R 15/l) 19(R 15/r) \n" +
                "1(B 2/l) 3(B 2/r) 6(B 7/l) 8(B 7/r) 11(B 12/l) 14(B 12/r) 17(B 19/l) 20(B 19/r) \n" +
                "4(R 3/r) 9(R 8/r) 16(R 17/l) 18(R 17/r) ";
        assertEquals(expect,rbTree.printTree());


        System.out.println("rm: 2(被删除节点是黑色，儿子为红色)");
        rbTree.remove(2);
        expect = "10(B) \n" +
                "5(B 10/l) 15(B 10/r) \n" +
                "3(R 5/l) 7(R 5/r) 12(R 15/l) 19(R 15/r) \n" +
                "1(B 3/l) 4(B 3/r) 6(B 7/l) 8(B 7/r) 11(B 12/l) 14(B 12/r) 17(B 19/l) 20(B 19/r) \n" +
                "9(R 8/r) 16(R 17/l) 18(R 17/r) ";
        assertEquals(expect,rbTree.printTree());

        System.out.println("rm: 10(case4：P为红色，S和儿子都不是红色)");
        rbTree.remove(10);
        expect = "11(B) \n" +
                "5(B 11/l) 15(B 11/r) \n" +
                "3(R 5/l) 7(R 5/r) 12(B 15/l) 19(R 15/r) \n" +
                "1(B 3/l) 4(B 3/r) 6(B 7/l) 8(B 7/r) 14(R 12/r) 17(B 19/l) 20(B 19/r) \n" +
                "9(R 8/r) 16(R 17/l) 18(R 17/r) ";
        assertEquals(expect,rbTree.printTree());

        rbTree.remove(3);


        System.out.println("rm: 1(被删除节点是红色)");
        rbTree.remove(1);
        expect = "11(B) \n" +
                "5(B 11/l) 15(B 11/r) \n" +
                "4(B 5/l) 7(R 5/r) 12(B 15/l) 19(R 15/r) \n" +
                "6(B 7/l) 8(B 7/r) 14(R 12/r) 17(B 19/l) 20(B 19/r) \n" +
                "9(R 8/r) 16(R 17/l) 18(R 17/r) ";
        assertEquals(expect,rbTree.printTree());

        System.out.println("rm: 12(3.2被删除节点是黑色，儿子为红色)");
        rbTree.remove(12);
        expect = "11(B) \n" +
                "5(B 11/l) 15(B 11/r) \n" +
                "4(B 5/l) 7(R 5/r) 14(B 15/l) 19(R 15/r) \n" +
                "6(B 7/l) 8(B 7/r) 17(B 19/l) 20(B 19/r) \n" +
                "9(R 8/r) 16(R 17/l) 18(R 17/r) ";
        assertEquals(expect,rbTree.printTree());


        rbTree.remove(8);

        System.out.println("rm: 20(case6：S是黑色，S的另一侧儿子为红色))");
        rbTree.remove(20);
        expect = "11(B) \n" +
                "5(B 11/l) 15(B 11/r) \n" +
                "4(B 5/l) 7(R 5/r) 14(B 15/l) 17(R 15/r) \n" +
                "6(B 7/l) 9(B 7/r) 16(B 17/l) 19(B 17/r) \n" +
                "18(R 19/l) ";
        assertEquals(expect,rbTree.printTree());

        rbTree.remove(5);

        System.out.println("rm: 16(case5：S是黑色，S的一侧(N同侧)儿子红，另一侧儿子为黑色))");
        rbTree.remove(16);
        expect = "11(B) \n" +
                "6(B 11/l) 15(B 11/r) \n" +
                "4(B 6/l) 7(B 6/r) 14(B 15/l) 18(R 15/r) \n" +
                "9(R 7/r) 17(B 18/l) 19(B 18/r) ";
        assertEquals(expect,rbTree.printTree());

        rbTree.remove(19);
        rbTree.remove(6);
        rbTree.remove(15);

        System.out.println("rm: 14(case3：P,S,S1,S2都是黑色");
        rbTree.remove(14);
        expect = "11(B) \n" +
                "7(R 11/l) 17(B 11/r) \n" +
                "4(B 7/l) 9(B 7/r) 18(R 17/r) ";
        assertEquals(expect,rbTree.printTree());

        rbTree.remove(11);

        System.out.println("rm: 17(case2：S节点为红色");
        rbTree.remove(17);
        expect = "7(B) \n" +
                "4(B 7/l) 18(B 7/r) \n" +
                "9(R) ";
        assertEquals(expect,rbTree.printTree());
        rbTree.remove(7);
        rbTree.remove(4);
        rbTree.remove(9);
        rbTree.remove(223333333);

        System.out.println("rm:18(case1: rm root)");
        rbTree.remove(18);
        assertEquals("",rbTree.printTree());
    }
}
