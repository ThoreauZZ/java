package com.thoreau.leetcode.leetcode.editor.cn;

/**
 * 2023/9/21 22:23.
 *
 * @author zhaozhou
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
    public static String listToString(ListNode head) {
        StringBuilder sb = new StringBuilder();
        ListNode current = head;

        // 遍历链表，将元素添加到 StringBuilder 中，并在元素之间添加逗号
        while (current != null) {
            sb.append(current.val);
            if (current.next != null) {
                sb.append(",");
            }
            current = current.next;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        // 初始化链表
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode fourth = new ListNode(4);
        ListNode fifth = new ListNode(5);
        head.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;

        // 将链表转换为逗号分隔的字符串
        String result = listToString(head);

        // 打印字符串
        System.out.println(result);
    }

    public static int[] listToArray(ListNode head) {
        // 先计算链表的长度
        int length = 0;
        ListNode current = head;
        while (current != null) {
            length++;
            current = current.next;
        }

        // 创建一个与链表长度相等的数组
        int[] arr = new int[length];

        // 将链表中的元素复制到数组中
        current = head;
        int index = 0;
        while (current != null) {
            arr[index] = current.val;
            index++;
            current = current.next;
        }

        return arr;
    }
}
