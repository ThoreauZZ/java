//给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
//
// 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处: 
//
// 
// 0 <= j <= nums[i] 
// i + j < n 
// 
//
// 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [2,3,1,1,4]
//输出: 2
//解释: 跳到最后一个位置的最小跳跃数是 2。
//     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
// 
//
// 示例 2: 
//
// 
//输入: nums = [2,3,0,1,4]
//输出: 2
// 
//
// 
//
// 提示: 
//
// 
// 1 <= nums.length <= 10⁴ 
// 0 <= nums[i] <= 1000 
// 题目保证可以到达 nums[n-1] 
// 
//
// Related Topics 贪心 数组 动态规划 👍 2284 👎 0


package com.thoreau.leetcode.leetcode.editor.cn;

public class L45_JumpGameIi {
    public static void main(String[] args) {
        Solution solution = new L45_JumpGameIi().new Solution();
        System.out.println(solution.jump(new int[]{2, 3, 1, 1, 4}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 贪心算法：每次尽可能跳最远
         * <p>
         * [2,3,1,1,4]
         * <p>
         * 如果是按贪心的思想，每次我都跳当前数字最大就好了。但从示例也看得出，其实不行。
         * <p>
         * 分析：
         * 如果i可以往前跳nums[i]步到j，也就是说，从i可以跳到i-j直接的任何节点，比如k。
         * 如果nums[k]+k>num[j]+j，其实我们应该选择少跳几步先从i->K，而不是从i->j。
         * <p>
         * 所以其实我们需要遍历i-j中的每一个元素，挑一个下一步能到大的最大位置。
         *
         * @param nums
         * @return
         */
        public int jump(int[] nums) {
            if (nums.length <= 1) {
                return 0;
            }
            int maxIndex = 0;
            int steps = 1;
            int j = 0;
            for (int i = 0; i <= j; i++) {
                maxIndex = Math.max(maxIndex, nums[i] + i);
                if (maxIndex >= nums.length - 1) {
                    // 已经到最后一个阶段
                    break;
                }
                if (i == j) {
                    // 也就是从i到j都查过，最大能跳到maxIndex，就是我们下一步要跳到的位置。
                    // 那maxIndex是k跳过去还是j跳过去先不管，这中间一定跳了一步，否则如
                    steps++;

                    // 1. 如果先到j后到maxIndex，那还是一样，下一个循环截至到maxIndex，j=maxIndex
                    // 2. 如果先到k后到maxIndex，无需再倒回k遍历，因为已经缺点，k->j能跳的不会超过maxIndex
                    //    倒回去遍历没有意义，还是从j-maxIndex
                    j = maxIndex;
                }

            }
            return steps;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}