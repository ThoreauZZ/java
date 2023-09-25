//编写一个函数来查找字符串数组中的最长公共前缀。
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["flower","flow","flight"]
//输出："fl"
// 
//
// 示例 2： 
//
// 
//输入：strs = ["dog","racecar","car"]
//输出：""
//解释：输入不存在公共前缀。 
//
// 
//
// 提示： 
//
// 
// 1 <= strs.length <= 200 
// 0 <= strs[i].length <= 200 
// strs[i] 仅由小写英文字母组成 
// 
//
// Related Topics 字典树 字符串 👍 2926 👎 0


package com.thoreau.leetcode.leetcode.editor.cn;

public class L14_LongestCommonPrefix {
    public static void main(String[] args) {
        Solution solution = new L14_LongestCommonPrefix().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) {
                return "";
            }
            // 取第一个作为前缀
            String prefix = strs[0];
            int count = strs.length;
            for (int i = 1; i < count; i++) {
                // 从第二个开始开始比对。分别返回最小公共子串
                prefix = longestCommonPrefix(prefix, strs[i]);
                if (prefix.length() == 0) {
                    // 出现一个步匹配的。之间结束
                    break;
                }
            }
            return prefix;
        }

        public String longestCommonPrefix(String str1, String str2) {
            int length = Math.min(str1.length(), str2.length());
            int index = 0;
            while (index < length && str1.charAt(index) == str2.charAt(index)) {
                index++;
            }
            return str1.substring(0, index);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}