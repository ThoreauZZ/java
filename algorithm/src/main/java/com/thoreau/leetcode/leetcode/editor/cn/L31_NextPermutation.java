//整数数组的一个 排列 就是将其所有成员以序列或线性顺序排列。
//
// 
// 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。 
// 
//
// 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就
//是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。 
//
// 
// 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。 
// 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。 
// 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。 
// 
//
// 给你一个整数数组 nums ，找出 nums 的下一个排列。 
//
// 必须 原地 修改，只允许使用额外常数空间。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[1,3,2]
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,1]
//输出：[1,2,3]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,1,5]
//输出：[1,5,1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 100 
// 
//
// Related Topics 数组 双指针 👍 2327 👎 0


package com.thoreau.leetcode.leetcode.editor.cn;

import static org.junit.Assert.assertEquals;

public class L31_NextPermutation {
    public static void main(String[] args) {
        Solution solution = new L31_NextPermutation().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 分析：[1,2,3,4,5,6] -> [1,2,3,4,6,5] -> [1,2,3,5,4,6] -> [1,2,3,5,6,4]
         *  [1,2,3,5,6,3]
         * 1. 从后向前，找到第一个比前一个要小的数字，此时，[i+1,length),一定是降序。
         * 2. 从后向前，找到第一个比如num[i]要大的（保证交换后序列更大，找最右一个，其实也就是最小的一个），位置为j。
         * 3. 交换两个位置，此时[i+1,length)还是降序。此时只需要把[i+1,length)反转即可
         *
         * 要求：必须原地修改，只允许使用额外常数空间。 是不是冒泡
         * @param nums
         */
        public void nextPermutation(int[] nums) {
            int length = nums.length;
            int i = length - 2;
            while (i >= 0 && nums[i] >= nums[i + 1]) {
                i--;
            }
            if (i >= 0) {
                // 不是递减
                int j = length - 1;
                while (j >= 0 && nums[j] <= nums[i]) {
                    // 找到比最右边比i位置大的数字。
                    j--;
                }
                swap(nums, i, j);
            }
            // 如果i=-1，表示串就是倒序的，全部反过来即可
            reverse(nums, i + 1);
        }
        public void swap(int[] nums,int i,int j){
            int tmp = nums[j];
            nums[j] = nums[i];
            nums[i] = tmp;
        }
        public void reverse(int[] nums,int i){
            int left = i, right = nums.length - 1;
            while (left < right) {
                swap(nums, left, right);
                left++;
                right--;
            }

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}