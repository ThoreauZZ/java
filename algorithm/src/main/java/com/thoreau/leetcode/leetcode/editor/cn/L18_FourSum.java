//给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[
//b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）： 
//
// 
// 0 <= a, b, c, d < n 
// a、b、c 和 d 互不相同 
// nums[a] + nums[b] + nums[c] + nums[d] == target 
// 
//
// 你可以按 任意顺序 返回答案 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,0,-1,0,-2,2], target = 0
//输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [2,2,2,2,2], target = 8
//输出：[[2,2,2,2]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// -10⁹ <= nums[i] <= 10⁹ 
// -10⁹ <= target <= 10⁹ 
// 
//
// Related Topics 数组 双指针 排序 👍 1764 👎 0


package com.thoreau.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L18_FourSum {
    public static void main(String[] args) {
        Solution solution = new L18_FourSum().new Solution();
        System.out.println(solution.fourSum(new int[]{1000000000,1000000000,1000000000,1000000000},-294967296));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 和三数和一样，双指针+排序。唯一区别是三数枚举一个数，4数枚举两个数
         * @param nums
         * @param target
         * @return
         */
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> result = new ArrayList<>();
            int n = nums.length;

            if (n < 4) {
                return result; // 如果数组长度小于4，不可能找到四元组
            }

            Arrays.sort(nums); // 排序数组

            for (int a = 0; a < n - 3; a++) {
                // 避免重复
                if (a > 0 && nums[a] == nums[a - 1]) {
                    continue;
                }
                for (int b = a + 1; b < n - 2; b++) {
                    // 避免重复
                    if (b > a + 1 && nums[b] == nums[b - 1]) {
                        continue;
                    }
                    int c = b + 1;
                    int d = n - 1;

                    while (c < d) {
                        long sum = (long)nums[a] + (long)nums[b] + (long)nums[c] + (long)nums[d];
                        if (sum == target) {
                            result.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
                            // 避免重复
                            while (c < d && nums[c] == nums[c + 1]) {
                                c++;
                            }
                            while (c < d && nums[d] == nums[d - 1]) {
                                d--;
                            }
                            c++;
                            d--;
                        } else if (sum < target) {
                            c++;
                        } else {
                            d--;
                        }
                    }
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}