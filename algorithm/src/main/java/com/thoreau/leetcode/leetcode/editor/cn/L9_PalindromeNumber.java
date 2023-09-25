//给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
//
// 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。 
//
// 
// 例如，121 是回文，而 123 不是。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：x = 121
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：x = -121
//输出：false
//解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
// 
//
// 示例 3： 
//
// 
//输入：x = 10
//输出：false
//解释：从右向左读, 为 01 。因此它不是一个回文数。
// 
//
// 
//
// 提示： 
//
// 
// -2³¹ <= x <= 2³¹ - 1 
// 
//
// 
//
// 进阶：你能不将整数转为字符串来解决这个问题吗？ 
//
// Related Topics 数学 👍 2674 👎 0


package com.thoreau.leetcode.leetcode.editor.cn;

public class L9_PalindromeNumber {
    public static void main(String[] args) {
        Solution solution = new L9_PalindromeNumber().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 思路：
         * 1. 首尾里两个指针向中间对比，但不能转为字符串。那就只能一个个字符取出来？再反转后半部分，看和前半部分是否相同
         * <p>
         * 【关键】如何知道反转数字的位数已经达到原始数字位数的一半？
         * 由于整个过程我们不断将原始数字除以10，然后给反转后的数字乘上10。 如果反转的部分位数小于原来的位数，那翻转
         * <p>
         * 特殊处理：0结尾和小于0步时回文，0本身是
         *
         * @param x
         * @return
         */
        public boolean isPalindrome(int x) {
            if (x < 0 || (x % 10 == 0 && x != 0)) {
                return false;
            }
            int reverse = 0;
            while (x > reverse) {
                reverse = reverse * 10 + x % 10;
                x = x / 10;
            }
            // 反转后，如果是奇数。12321，123/10 位数相同。如果是偶数位。 1221，反转后他两就是相等的。122 221
            return reverse == x || x == reverse/10;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}