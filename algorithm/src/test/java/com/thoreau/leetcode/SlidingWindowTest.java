package com.thoreau.leetcode;

import com.thoreau.algorithm.dynamicprogramming.DP01;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * 2023/9/10 22:56.
 *
 * @author zhaozhou
 */
public class SlidingWindowTest{
    private SlidingWindow sw = new SlidingWindow();

    @Test
    public void lengthOfLongestSubstringTest() {
        System.out.println(sw.lengthOfLongestSubstring("abcabcbb"));
    }

}