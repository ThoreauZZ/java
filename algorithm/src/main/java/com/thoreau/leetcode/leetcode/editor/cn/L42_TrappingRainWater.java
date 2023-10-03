//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
// 
//
// 示例 2： 
//
// 
//输入：height = [4,2,0,3,2,5]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// n == height.length 
// 1 <= n <= 2 * 10⁴ 
// 0 <= height[i] <= 10⁵ 
// 
//
// Related Topics 栈 数组 双指针 动态规划 单调栈 👍 4743 👎 0


package com.thoreau.leetcode.leetcode.editor.cn;

public class L42_TrappingRainWater {
    public static void main(String[] args) {
        Solution solution = new L42_TrappingRainWater().new Solution();
        System.out.println(solution.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         *
         * 双指针：移动一位，只看这一位的雨水合法性，不用考虑其他是否有效，想象为统计指针位置的雨水。
         *       移动矮的那一边的墙。这个过程中，如果比上一次的最矮的高，相当于这个位置的
         */
        public int trap(int[] height) {
            // 初始化总的蓄水量为0
            int ans = 0;

            // 初始化左右两个指针，分别指向数组的第一个和最后一个元素
            int left = 0, right = height.length - 1;

            // 初始化左边最高的墙和右边最高的墙的高度
            int leftMax = 0, rightMax = 0;

            // 使用双指针法来遍历数组，直到左指针小于右指针
            while (left < right) {
                // 更新左边最高的墙的高度为当前左指针所指的墙和之前的左边最高墙中的较大值
                leftMax = Math.max(leftMax, height[left]);

                // 更新右边最高的墙的高度为当前右指针所指的墙和之前的右边最高墙中的较大值
                rightMax = Math.max(rightMax, height[right]);

                // 如果左边的墙较矮，则计算当前位置能蓄水的量，即左边最高墙的高度减去当前墙的高度，然后累加到总的蓄水量中
                if (height[left] < height[right]) {
                    ans += leftMax - height[left];

                    // 移动左指针向右以继续比较下一个墙
                    ++left;
                } else {
                    // 如果右边的墙较矮，同样计算蓄水量并累加
                    ans += rightMax - height[right];

                    // 移动右指针向左以继续比较下一个墙
                    --right;
                }
            }

            // 返回最终的蓄水量
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}