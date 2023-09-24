package com.thoreau.algorithm.list;

/**
 * 2019/3/16 8:59 PM.
 *
 * @author zhaozhou
 */
public class SingleLinkNode {
    private int value;
    SingleLinkNode next;

    public SingleLinkNode(int value) {
        this.value = value;
    }

    public void setNext(SingleLinkNode next) {
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public static void insert(SingleLinkNode head, int value) {
        if (head.next != null) {
            insert(head.next, value);
        } else {
            head.next = new SingleLinkNode(value);
            return;
        }
    }

    public void print() {
        SingleLinkNode next = this;
        while (next != null) {
            System.out.print(next.value + "-->");
//            System.out.println(next.hashCode() + "  = " + next.value);
//            System.out.println("    -->" + (next.next == null ? null : next.next.hashCode()));
            next = next.next;
        }
    }

    public SingleLinkNode reverse(SingleLinkNode head) {
        SingleLinkNode pre = null;
        SingleLinkNode cur = head;
        while (cur != null) {
            SingleLinkNode nextTmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nextTmp;
        }
        return pre;
    }

    public SingleLinkNode reverse2(SingleLinkNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        SingleLinkNode reverse = reverse2(head.next);
        head.next.next = head;
        head.next = null;
        return reverse;
    }
}
