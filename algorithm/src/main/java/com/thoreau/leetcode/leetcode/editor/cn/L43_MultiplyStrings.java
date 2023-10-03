//给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
//
// 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。 
//
// 
//
// 示例 1: 
//
// 
//输入: num1 = "2", num2 = "3"
//输出: "6" 
//
// 示例 2: 
//
// 
//输入: num1 = "123", num2 = "456"
//输出: "56088" 
//
// 
//
// 提示： 
//
// 
// 1 <= num1.length, num2.length <= 200 
// num1 和 num2 只能由数字组成。 
// num1 和 num2 都不包含任何前导零，除了数字0本身。 
// 
//
// Related Topics 数学 字符串 模拟 👍 1269 👎 0


package com.thoreau.leetcode.leetcode.editor.cn;

public class L43_MultiplyStrings {
    public static void main(String[] args) {
        Solution solution = new L43_MultiplyStrings().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 方法一：创建一个结果数组，用于存储每位的乘积结果。
         * 从两个输入字符串的最后一位（个位）开始，逐位相乘，并将结果添加到正确的位置上。
         * 处理进位情况，确保结果数组中的每位都在0到9之间。
         * 将结果数组转换为字符串，并去掉前导零。
         *
         *
         */
        public String multiply(String num1, String num2) {
            if (num1.equals("0") || num2.equals("0")) {
                return "0"; // 特殊情况处理
            }
            int m = num1.length();
            int n = num2.length();

            /*
                1  2  3  m=3
                   1  4  n=2
                --------
                4  9  2
             1  2  3
             ------------
             result[0]=1,      result[3]= num1[2]+num2[1] = 12 % 10
             也就是说 i+j 就是存储这两个数字的位置。
             但有个问题，如果最前面的一位进位，就没地方存储了，所以 应该是i+j+1,进位保持在i+j
             */
            int[] result = new int[m + n]; // 存储乘积的每一位

            // 从低位到高位逐位相乘并累加到结果数组
            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                    int p1 = i + j; // 乘积的高位
                    int p2 = i + j + 1; // 乘积的低位
                    int sum = product + result[p2]; // 直接累加乘积结果
                    result[p1] += sum / 10; // 处理进位
                    result[p2] = sum % 10;
                }
            }
            StringBuilder sb = new StringBuilder(m + n);
            for (int digit : result) {
                sb.append(digit);
            }
            // 去掉前导零
            if (result[0] == 0) {
                sb.deleteCharAt(0);
            }
            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}