//给你两个整数，被除数 dividend 和除数 divisor。将两数相除，要求 不使用 乘法、除法和取余运算。
//
// 整数除法应该向零截断，也就是截去（truncate）其小数部分。例如，8.345 将被截断为 8 ，-2.7335 将被截断至 -2 。 
//
// 返回被除数 dividend 除以除数 divisor 得到的 商 。 
//
// 注意：假设我们的环境只能存储 32 位 有符号整数，其数值范围是 [−2³¹, 231 − 1] 。本题中，如果商 严格大于 231 − 1 ，则返回 2
//31 − 1 ；如果商 严格小于 -2³¹ ，则返回 -2³¹ 。 
//
// 
//
// 示例 1: 
//
// 
//输入: dividend = 10, divisor = 3
//输出: 3
//解释: 10/3 = 3.33333.. ，向零截断后得到 3 。 
//
// 示例 2: 
//
// 
//输入: dividend = 7, divisor = -3
//输出: -2
//解释: 7/-3 = -2.33333.. ，向零截断后得到 -2 。 
//
// 
//
// 提示： 
//
// 
// -2³¹ <= dividend, divisor <= 2³¹ - 1 
// divisor != 0 
// 
//
// Related Topics 位运算 数学 👍 1176 👎 0


package com.thoreau.leetcode.leetcode.editor.cn;

import static org.junit.Assert.assertEquals;

public class L29_DivideTwoIntegers {
    public static void main(String[] args) {
        Solution solution = new L29_DivideTwoIntegers().new Solution();
        assertEquals(4, solution.divide(8, 2));
        assertEquals(2, solution.divide(8, 3));
        assertEquals(0, solution.divide(1, 3));
        assertEquals(0, solution.divide(0, 3));
        assertEquals(-1, solution.divide(-1, 1));
        assertEquals(1, solution.divide(-1, -1));
        assertEquals(2147483647, solution.divide(-2147483648, -1));
        assertEquals(-2147483647, solution.divide(-2147483648, 1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：除法本质是减法，循环减去divisor即可。
         * 优化：纯减法效率很低，可以对divisor移位，移动一次，意味着divisor^2个，
         *      也就是一次减divisor很慢，那就一次减2个divisor，4个divisor，8.....
         */
        public int divide(int dividend, int divisor) {
            int sign = (dividend < 0 ^ divisor < 0) ? -1 : 1;
            long absDividend = Math.abs((long) dividend);
            long absDivisor = Math.abs((long)divisor);
            long result = 0;
            while (absDividend >= absDivisor) {
                long tmp = absDivisor;
                long multi = 1;
                while (absDividend >= (tmp << 1)) {
                    tmp <<= 1;
                    multi <<= 1;
                }
                absDividend = absDividend - tmp;
                result = result + multi;
            }
            result = sign < 0 ? - result :  result;
            if (result > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (result < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            return (int) result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}