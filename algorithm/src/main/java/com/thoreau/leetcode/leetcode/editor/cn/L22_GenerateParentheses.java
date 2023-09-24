//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：["()"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 8 
// 
//
// Related Topics 字符串 动态规划 回溯 👍 3386 👎 0


package com.thoreau.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class L22_GenerateParentheses {
    public static void main(String[] args) {
        Solution solution = new L22_GenerateParentheses().new Solution();
        assertEquals(new String[]{"()"}, solution.generateParenthesis(1).toArray());
        assertEquals(new String[]{"(())","()()"}, solution.generateParenthesis(2).toArray());
        assertEquals(new String[]{"((()))","(()())","(())()","()(())","()()()"}, solution.generateParenthesis(3).toArray());
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：要找出所有可能的组合，一种常见的算法思想是回溯算法。
         * 回溯算法通常用于解决组合、排列、子集等问题，它的基本思想是在搜索过程中不断尝试各种可能性，
         * 并在不符合条件的情况下回退（回溯）到上一个状态，继续尝试其他可能性，直到找到满足条件的解或者穷尽所有可能性。
         * <p>
         * 根据这个思路，只需要排除无效的组合即可，
         * <p>
         * 是否有效：可以记录括号的方向，如果出现')' 大于 '(',或者已经遍历了n对，步相等。
         */
        public List<String> generateParenthesis(int n) {
            List<String> list = new ArrayList<>();
            dfs("", n, n, list);
            return list;
        }

        private void dfs(String s, int left, int right, List<String> list) {
            if (left < 0 || right < 0) {
                return;
            }
            if (left > right) {
                return;
            }
            if (left == right && left == 0) {
                list.add(s);
                return;
            }
            dfs(s + "(", --left, right, list);
            ++left;
            dfs(s + ")", left, --right, list);
            ++right;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}