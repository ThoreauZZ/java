//实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，xⁿ ）。
//
// 
//
// 示例 1： 
//
// 
//输入：x = 2.00000, n = 10
//输出：1024.00000
// 
//
// 示例 2： 
//
// 
//输入：x = 2.10000, n = 3
//输出：9.26100
// 
//
// 示例 3： 
//
// 
//输入：x = 2.00000, n = -2
//输出：0.25000
//解释：2-2 = 1/22 = 1/4 = 0.25
// 
//
// 
//
// 提示： 
//
// 
// -100.0 < x < 100.0 
// -231 <= n <= 231-1 
// n 是一个整数 
// 要么 x 不为零，要么 n > 0 。 
// -104 <= xⁿ <= 104 
// 
//
// Related Topics 递归 数学 👍 1250 👎 0


package com.thoreau.leetcode.leetcode.editor.cn;

public class L50_PowxN {
    public static void main(String[] args) {
        Solution solution = new L50_PowxN().new Solution();
        System.out.println(solution.myPow(3, 5));
        System.out.println(solution.myPow(3, 3));
        System.out.println(solution.myPow(3, 4));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 快速幂：x^4= x^2 * x^2; 3^4=81=3^3*3^3=9x9=81 上一次的结果进行平方这样进行类陈，
         * 另外一致二分法思想
         * 如果n是2的倍数：
         *  x^8 = (x^2)
         *        (x^2 * x^2)
         *        x^4 * x^4
         *  3次相乘刚好结束；
         *
         *  x^6= (x^2)
         *       (x^2 * x^2)
         *       (x^4) * x * x  （也就是还要x 8-4=2个x）
         *
         * 如果N越大，2^i到N的距离较大，就会再计算多次，效率还是不行。其实值需要在对一对二进制位置是1时，x2即可。
         *
         *
         */
        public double myPow(double x, int n) {
            if (x == 0.0f) return 0.0d;
            long b = n;
            double res = 1.0;
            if (b < 0) {
                x = 1 / x;
                b = -b;
            }
            while (b > 0) {
                if ((b & 1) == 1) {
                    // 奇数
                    res *= x;
                }
                // 值平方
                x *= x;
                // 右移一位
                b >>= 1;
            }
            return res;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}