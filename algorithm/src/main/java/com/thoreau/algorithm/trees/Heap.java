package com.thoreau.algorithm.trees;

/**
 * 2023/1/15 12:41.
 *
 * @author zhaozhou
 */
public class Heap {
    private int[] a;
    // 容纳元素个数
    private int capacity;
    // 当前元素数量
    private int count;

    public Heap(int capacity) {
        a = new int[capacity + 1];
        this.capacity = capacity;
        count = 0;
    }

    private void insert(int data) {
        if (count >= capacity) {
            return;
        }
        count++;
        // 先放中最后
        a[count] = data;
        int i = count;
        while (i / 2 > 0 && a[i] > a[i / 2]) {
            // 大于父节点
            swap(a, i, i / 2);
            i = i / 2;
        }
    }

    public void removeMax() {
        if (count == 0) {
            return;
        }
        a[1] = a[count];
        count--;
        heapify(a, count, 1);
    }

    private void buildHeap(int[] a, int n) {
        for (int i = n / 2; i >= 1; --i) {
            heapify(a, n, i);
        }
    }

    private void heapify(int[] a, int count, int i) {
        while (true) {
            int maxPos = i;
            if (i * 2 <= capacity && a[maxPos] < a[i * 2]) {
                maxPos = i * 2;
            }
            if (i * 2 + 1 <= capacity && a[maxPos] < a[i * 2 + 1]) {
                maxPos = i * 2 + 1;
            }
            if (maxPos == i) {
                break;
            }
            swap(a, i, maxPos);
            i = maxPos;
        }
    }

    private void swap(int[] a, int i1, int i2) {
        int tmp = a[i1];
        a[i1] = a[i2];
        a[i2] = tmp;
    }

    private void sort(int[] a, int n) {
        buildHeap(a, n);
        int k = n;
        while (k > 1) {
            swap(a, 1, k);
            --k;
            heapify(a, k, 1);
        }
    }

    public static void main(String[] args) {
        Heap heap = new Heap(8);
        heap.insert(11);
        heap.insert(20);
        heap.insert(12);
        heap.insert(1);
        heap.insert(9);
        heap.insert(2);
        heap.insert(18);
        heap.insert(15);

        int[] a = new int[]{11, 20, 12, 1, 9, 2, 18, 15};
        for (int i = 0; i < a.length; i++) {

        }
    }
}
