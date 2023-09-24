package com.thoreau.algorithm.search;

/**
 * 2023/1/8 17:28.
 * <p>
 * 缺点
 * 1. 二分查找针对的是有序数据
 * 2. 二分查找依赖的是顺序表结构，简单点说就是数组
 * 3. 数据量太小不适合二分查找
 * 4. 后，数据量太大也不适合二分查找。持随机访问的特性，要求内存空间连 续，对内存的要求比较苛刻
 *
 * @author zhaozhou
 */
public class BSearch {
    /**
     * 最简单的二分查找
     */
    public static int bsearch(int[] a, int v) {
        int left = 0;
        int right = a.length - 1;
        // 注意是left <= right，不是left < right
        while (left <= right) {
//            int mid = left + (right - left) / 2;
            int mid = left + (right - left) >> 1;
            if (a[mid] > v) {
                right = mid - 1;
            } else if (a[mid] < v) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 简单的二分查找递归实现
     */
    private int bsearchInternally(int[] a, int low, int high, int value) {
        if (low > high) return -1;
        int mid = low + ((high - low) >> 1);
        if (a[mid] == value) {
            return mid;
        } else if (a[mid] < value) {
            return bsearchInternally(a, mid + 1, high, value);
        } else {
            return bsearchInternally(a, low, mid - 1, value);
        }
    }

    /**
     * 查找第一个值等于给定值的元素
     */
    public int bsearch_1(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                if ((mid == 0) || (a[mid - 1] != value)) return mid;
                else high = mid - 1;
            }
        }
        return -1;
    }

    /**
     *  查找第一个大于等于给定值的元素
     */
    public int bsearch_2(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] >= value) {
                if ((mid == 0) || (a[mid - 1] < value)) return mid;
                else high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
}
