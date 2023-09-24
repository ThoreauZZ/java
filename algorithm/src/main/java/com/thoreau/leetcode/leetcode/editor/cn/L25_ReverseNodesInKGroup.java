 //给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。 
//
// k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 
//
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。 
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,2,3,4,5], k = 2
//输出：[2,1,4,3,5]
// 
//
// 示例 2： 
//
// 
//
// 
//输入：head = [1,2,3,4,5], k = 3
//输出：[3,2,1,4,5]
// 
//
// 
//提示：
//
// 
// 链表中的节点数目为 n 
// 1 <= k <= n <= 5000 
// 0 <= Node.val <= 1000 
// 
//
// 
//
// 进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？ 
//
// 
// 
//
// Related Topics 递归 链表 👍 2169 👎 0

  
  package com.thoreau.leetcode.leetcode.editor.cn;
  public class L25_ReverseNodesInKGroup{
      public static void main(String[] args) {
           Solution solution = new L25_ReverseNodesInKGroup().new Solution();
      }
      //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    /**
     * 1. 递归方式；
     * 2. 循环方式
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;

        while (head != null) {
            ListNode tail = pre;

            // 移动右指针
            for (int i = 0; i < k; ++i) {
                tail = tail.next;
                if (tail == null) {
                    // 节点不够一组k
                    return dummy.next;
                }
            }

            // 先保存下一组的第一个节点
            ListNode nex = tail.next;

            // 反转k个：因为已经保存了对应节点，可以认为k个节点的头尾都没节点，实际上也要这么处理。
            ListNode[] reverse = myReverse(head, tail);
            head = reverse[0];
            tail = reverse[1];

            // 把子链表重新接回原链表
            pre.next = head;
            tail.next = nex;
            pre = tail;
            head = tail.next;
        }
        return dummy.next;

    }

    private ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode prev = null;
        ListNode cur = head;
        while (prev != tail) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return new ListNode[]{tail, head};
    }

    // 1->2->3->4->5
    // 1->2->4->3->5
    // 辅助函数：翻转链表的前 k 个节点
    private ListNode reverseLinkedList(ListNode head, int k) {
        ListNode prev = null;
        ListNode current = head;

        // 翻转前 k 个节点
        for (int i = 0; i < k; i++) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

  }