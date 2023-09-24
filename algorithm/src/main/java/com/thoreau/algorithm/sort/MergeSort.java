package com.thoreau.algorithm.sort;

/**
 * 2023/1/2 14:55.
 *
 * @author zhaozhou
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] array = {7,2,4,6,5,1,8,0,3};
    }
    /**
     *将待排序的数列不断地二分，直到每个子序列都只包含一个元素，然后将这些子序列合并成一个有序序列。合并的过程就是排序的过程。
     */
    public static void mergeSort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    private static void sort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        sort(array, left, mid);
        sort(array, mid + 1, right);
        merge(array, left, right);
    }
    private static void merge(int[] arr, int left, int right) {
        int mid = (left + right) / 2;
        int[] tmp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        // 对比两个片段并移动指针
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                tmp[k++] = arr[i++];
            } else {
                tmp[k++] = arr[j++];
            }
        }
        // 没有遍历完成的继续追加
        while (i <= mid) {
            tmp[k++] = arr[i++];
        }
        while (j <= right) {
            tmp[k++] = arr[j++];
        }
        // 拷贝tmp到源数组
        for (int l = 0; l < k; l++) {
            arr[left++] = tmp[l];
        }
    }
}
