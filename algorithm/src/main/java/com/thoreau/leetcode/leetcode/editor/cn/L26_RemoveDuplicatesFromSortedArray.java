//给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持
//一致 。然后返回 nums 中唯一元素的个数。 
//
// 考虑 nums 的唯一元素的数量为 k ，你需要做以下事情确保你的题解可以被通过： 
//
// 
// 更改数组 nums ，使 nums 的前 k 个元素包含唯一元素，并按照它们最初在 nums 中出现的顺序排列。nums 的其余元素与 nums 的大小不
//重要。 
// 返回 k 。 
// 
//
// 判题标准: 
//
// 系统会用下面的代码来测试你的题解: 
//
// 
//int[] nums = [...]; // 输入数组
//int[] expectedNums = [...]; // 长度正确的期望答案
//
//int k = removeDuplicates(nums); // 调用
//
//assert k == expectedNums.length;
//for (int i = 0; i < k; i++) {
//    assert nums[i] == expectedNums[i];
//} 
//
// 如果所有断言都通过，那么您的题解将被 通过。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,2]
//输出：2, nums = [1,2,_]
//解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,0,1,1,1,2,2,3,3,4]
//输出：5, nums = [0,1,2,3,4]
//解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 10⁴ 
// -10⁴ <= nums[i] <= 10⁴ 
// nums 已按 非严格递增 排列 
// 
//
// Related Topics 数组 双指针 👍 3336 👎 0


package com.thoreau.leetcode.leetcode.editor.cn;

public class L26_RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {
        Solution solution = new L26_RemoveDuplicatesFromSortedArray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 更改数组 nums ，使 nums 的前 k 个元素包含唯一元素，并按照它们最初在 nums 中出现的顺序排列。nums 的其余元素与 nums 的大小不重要。
         *
         * 关键：把唯一元素诺到前面就行
         *
         *  [0,0,1,1,1,2,2,3,3,4]
         *
         *  思路：双指针，右边指针遇到相同的就继续移动，遇到不同的就把元素覆盖到slow+1
         *
         */
        public int removeDuplicates(int[] nums) {
            int slow = 1, fast = 1;
            while (fast < nums.length) {
                if (nums[fast] != nums[fast - 1]) {
                    nums[slow] = nums[fast];
                    // 找到和前一位不一样的，放中慢指针的下一位。 快指针中间遇到过的不用着急替换
                    ++slow;
                }
                // 每次移动
                ++fast;
            }
            return slow;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}