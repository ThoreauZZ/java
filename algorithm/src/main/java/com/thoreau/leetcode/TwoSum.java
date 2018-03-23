package com.thoreau.leetcode;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        if (nums == null || nums.length <= 1) {
            throw new NumberFormatException();
        }
        for (int i = 0; i < nums.length; map.put(nums[i], i++)) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
        }
        return new int[2];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int[] rst = twoSum(nums, 9);
        for (int i = 0; i < rst.length; i++) {
            System.out.println(rst[i]);
        }
    }
}
