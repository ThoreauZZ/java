package com.thoreau.algorithm.list;

import org.junit.Before;
import org.junit.Test;

/**
 * 2019/3/16 9:03 PM.
 *
 * @author zhaozhou
 */
public class SingleLinkNodeTest {
    private SingleLinkNode head = null;
    @Before
    public void init() {
        head = new SingleLinkNode(0);
        for (int i = 1; i < 6; i++) {
            SingleLinkNode.insert(head,i);
        }
    }


    @Test
    public void print() {
        head.print();
    }
    @Test
    public void reverseTest(){
        head.print();
        System.out.println();
        SingleLinkNode reverse = head.reverse(head);
        reverse.print();

    }
    @Test
    public void reverseTest2(){
        head.print();
        System.out.println();
        SingleLinkNode reverse = head.reverse2(head);
        reverse.print();

    }
}