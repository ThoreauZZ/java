//给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,2,3,4]
//输出：[2,1,4,3]
// 
//
// 示例 2： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 100] 内 
// 0 <= Node.val <= 100 
// 
//
// Related Topics 递归 链表 👍 2027 👎 0


package com.thoreau.leetcode.leetcode.editor.cn;

public class L24_SwapNodesInPairs {
    public static void main(String[] args) {
        Solution solution = new L24_SwapNodesInPairs().new Solution();
        // 创建链表节点
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode fourth = new ListNode(4);
        ListNode fifth = new ListNode(5);

        // 连接链表节点
        head.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;

        System.out.println(ListNode.listToString(solution.swapPairs(head)));
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        /**
         * 1->2->3->4
         * 2->1->3->4
         *
         * @param head
         * @return
         */
        public ListNode swapPairs(ListNode head) {
            if (head == null) {
                return null;
            }
            // 创建一个哑结点，简化边界情况处理
            ListNode dummy = new ListNode(0);
            dummy.next = head;

            ListNode prev = dummy;
            ListNode first = head;
            ListNode second = head.next;

            while (first != null && second != null) {

                // 交换相邻两个结点: 0->1->2->3 -- 02,13,21的顺序
                prev.next = second;
                first.next = second.next;
                second.next = first;

                // 更新 prev，first，second 的位置
                prev = first;
                first = first.next;
                if (first != null) {
                    second = first.next;
                }
            }

            return dummy.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}