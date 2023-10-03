//给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1]
//输出：[[0,1],[1,0]]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1]
//输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 6 
// -10 <= nums[i] <= 10 
// nums 中的所有整数 互不相同 
// 
//
// Related Topics 数组 回溯 👍 2706 👎 0


package com.thoreau.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class L46_Permutations {
    public static void main(String[] args) {
        Solution solution = new L46_Permutations().new Solution();
        System.out.println(solution.permute(new int[]{1, 2, 3}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * [1,2,3]
         * <p>
         * DFS回溯。
         * 枚举每一个元素开头，第二个元素
         */
        public List<List<Integer>> permute(int[] nums) {
            int len = nums.length;
            List<List<Integer>> res = new ArrayList<>();
            if (len == 0) {
                return res;
            }
            boolean[] used = new boolean[len];
            List<Integer> path = new ArrayList<>();
            dfs(nums, len, 0, path, used, res);
            return res;
        }


        private void dfs(int[] nums, int len, int depth,
                         List<Integer> path, boolean[] used,
                         List<List<Integer>> res) {
            if (depth == len) {
                res.add(new ArrayList<>(path));
                return;
            }
            // 枚举每一个节点开始，核心是used的使用。
            for (int i = 0; i < len; i++) {
                if (!used[i]) {
                    path.add(nums[i]);
                    used[i] = true;
                    dfs(nums, len, depth + 1, path, used, res);
                    // 回溯
                    used[i] = false;
                    path.remove(path.size() - 1);
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}