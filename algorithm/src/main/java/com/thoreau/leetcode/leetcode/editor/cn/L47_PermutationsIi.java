//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 8 
// -10 <= nums[i] <= 10 
// 
//
// Related Topics 数组 回溯 👍 1468 👎 0


package com.thoreau.leetcode.leetcode.editor.cn;

import java.util.*;

public class L47_PermutationsIi {
    public static void main(String[] args) {
        Solution solution = new L47_PermutationsIi().new Solution();
        System.out.println(solution.permuteUnique(new int[]{1, 1, 2}));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 重复问题解决：
         * 1. 同位置(层)不能重复。不同位置的话，跟 全排列问题 一样。
         *
         * @param nums
         * @return
         */
        public List<List<Integer>> permuteUnique(int[] nums) {
            int len = nums.length;
            List<List<Integer>> res = new ArrayList<>();
            if (len == 0) {
                return res;
            }
            boolean[] used = new boolean[len];
            List<Integer> path = new ArrayList<>();
            Arrays.sort(nums);
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
            for (int i = 0; i < len; i++) {
                /**
                 *
                 *  重复问题，需要解决这个循环里面的相同数值不重复使用。
                 *  case：[1, 1, 2], [1, 2, 1] 之后，会一致递归出账，used变成[false,false,false],
                 *       第二轮循环，开始选择第二个数开始，因为1和前一个数相同，并且在used数组中他没用到过，只能说明是同层级的情况。
                 *
                 *  解决：1. 新建一个hash表，记录每一个层级用的数字。
                 *       2. 先排序，这里判断i vs i-1的值相同,并且i-1没有被使用过,就跳过
                 */
                if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
                    // !used[i - 1] 实现了「同层剪枝」，而 used[i - 1] 实现「非同层剪枝」。
                    continue;
                }
                path.add(nums[i]);
                used[i] = true;
                dfs(nums, len, depth + 1, path, used, res);
                // 回溯
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}