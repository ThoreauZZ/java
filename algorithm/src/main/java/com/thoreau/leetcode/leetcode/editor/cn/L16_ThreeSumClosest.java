//给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
//
// 返回这三个数的和。 
//
// 假定每组输入只存在恰好一个解。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,2,1,-4], target = 1
//输出：2
//解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,0,0], target = 1
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 3 <= nums.length <= 1000 
// -1000 <= nums[i] <= 1000 
// -10⁴ <= target <= 10⁴ 
// 
//
// Related Topics 数组 双指针 排序 👍 1516 👎 0


package com.thoreau.leetcode.leetcode.editor.cn;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class L16_ThreeSumClosest {
    public static void main(String[] args) {
        Solution solution = new L16_ThreeSumClosest().new Solution();
        assertEquals(0, solution.threeSumClosest(new int[]{0,0,0}, 1));
        assertEquals(2, solution.threeSumClosest(new int[]{-1,2,1,-4}, 1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 排序+双指针
         * <p>
         * 固定一个数，for循环枚举。 双指针+固定数 ~ target。双指针-（固定数-target)=0
         */
        public int threeSumClosest(int[] nums, int target) {
            Arrays.sort(nums);
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length - 2; i++) {
                if (i > 0 && nums[i] == nums[i - 1]) {
                    // 相同的就不再枚举了。
                    continue;
                }
                int left = i + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[left] + nums[right];
                    if (sum == target) {
                        return target;
                    }
                    if (Math.abs(sum - target) < Math.abs(min - target)) {
                        min = sum;
                    }
                    if (sum > target) {
                        // 如果和大于 target，移动 c 对应的指针
                        right--;
                        // 移动到下一个不相等的元素
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    } else {
                        left++;
                        // 移动到下一个不相等的元素
                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }
                    }
                }
            }
            return min;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}