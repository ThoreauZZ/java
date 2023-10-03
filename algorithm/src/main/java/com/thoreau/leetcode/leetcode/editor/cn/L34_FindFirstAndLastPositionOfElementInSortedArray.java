//给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。 
//
// 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4] 
//
// 示例 2： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1] 
//
// 示例 3： 
//
// 
//输入：nums = [], target = 0
//输出：[-1,-1] 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 10⁵ 
// -10⁹ <= nums[i] <= 10⁹ 
// nums 是一个非递减数组 
// -10⁹ <= target <= 10⁹ 
// 
//
// Related Topics 数组 二分查找 👍 2481 👎 0


package com.thoreau.leetcode.leetcode.editor.cn;

import java.util.Arrays;

public class L34_FindFirstAndLastPositionOfElementInSortedArray {
    public static void main(String[] args) {
        Solution solution = new L34_FindFirstAndLastPositionOfElementInSortedArray().new Solution();
        System.out.println(Arrays.toString(solution.searchRange(new int[]{5, 7, 7, 8, 8,8,8, 10}, 8)));
        System.out.println(Arrays.toString(solution.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6)));
        System.out.println(Arrays.toString(solution.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 10)));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 输入：nums = [5,7,7,8,8,10], target = 8
         * 输出：[3,4]
         * <p>
         * 思路：二分查找，找到之后, 再左边二分找到左边，右边二分。
         * 优化：之间一次找到做边，一次找到右边即可。
         *
         */
        public int[] searchRange(int[] nums, int target) {
            int[] result = new int[]{-1, -1};
            if (nums.length == 0) {
                return result;
            }
            int left = findLeftBoundary(nums, target);
            int right = findRightBoundary(nums, target);
            result[0] = left;
            result[1] = right;

            return result;
        }

        // 查找左边界
        private int findLeftBoundary(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    right = mid - 1; // 向左收缩右边界。如果收后<target，其实left指针会往右边靠的。直到相等
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            if (left >= nums.length || nums[left] != target) {
                return -1;
            }

            return left;
        }

        // 查找右边界
        private int findRightBoundary(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    left = mid + 1; // 向右收缩左边界
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            if (right < 0 || nums[right] != target) {
                return -1;
            }

            return right;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}