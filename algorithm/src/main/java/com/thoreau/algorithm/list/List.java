package com.thoreau.algorithm.list;

/**
 * 2018/2/17 下午11:36.
 *
 * @author zhaozhou
 */
public class List {

    class SingleLinkNode {
        int val;
        SingleLinkNode next;
        public void setNext(SingleLinkNode next) {
            this.next = next;
        }
    }

    class Node{
        int val;
        Node prev;
        Node next;
        Node(int val) {
            this.val = val;
            this.prev = this.next = null;
        }
    }
    public Node reverse(Node node) {
        while (node != null) {
            Node next = node.next;
            Node prev = node.prev;
            node.prev = next;
            node.next = prev;
            node = node.next;
        }
        return node;
    }
    
}
