package com.thoreau.algorithm.sort;

/**
 * 2023/1/6 16:54.
 *
 * @author zhaozhou
 */
public class QuickSort {
    /**
     * @param array
     */
    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = partition(array, left, right);
        quickSort(array, left, pivot - 1);
        quickSort(array, pivot + 1, right);
    }

    private static int partition(int[] array, int left, int right) {
        System.out.println("partition-left="+left+",right="+right);
        dealPivot(array, left, right);
        int base = array[right];
        int i = left;
        int j = right - 1;
        while (true) {
            while (array[i] < base) {
                // 左边指针往右边移动
                i++;
            }
            while (j > i && array[j] > base) {
                j--;
            }
            if (i < j) {
                swap(array, i, j);
            } else {
                // i >= j ：说明i是i左边最大的数值； 又因为i>=base，所以和base交换，完成所有数据分组
                swap(array, i, right);
                break;
            }
        }
        return i;
    }

    /**
     * 三数取中法
     */
    public static void dealPivot(int[] arr, int left, int right) {
        int mid = (left + right) / 2;
        if (arr[left] > arr[mid]) {
            swap(arr, left, mid);
        }
        if (arr[left] > arr[right]) {
            swap(arr, left, right);
        }
        if (arr[right] < arr[mid]) {
            swap(arr, right, mid);
        }
        swap(arr, right, mid);
    }

    public static void quickSortOptimize1(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = partition(array, left, right);
        quickSort(array, left, pivot - 1);
        quickSort(array, pivot + 1, right);
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
