package com.thoreau.algorithm.sort;

/**
 * 2023/1/1 13:13.
 *
 * @author zhaozhou
 */
public class InsertSort {
    public void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1;
            // 这一步是实现原地排序的关键
            while (j >= 0 && array[j] > temp) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
        }
    }

    public void insertSortWithBinarySearch(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int left = 0;
            int right = i - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (temp < array[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            for (int j = i - 1; j >= left; j--) {
                array[j + 1] = array[j];
            }
            array[left] = temp;
        }
    }


}
