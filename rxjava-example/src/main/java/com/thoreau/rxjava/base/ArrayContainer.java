package com.thoreau.rxjava.base;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 2018/3/27 17:28.
 *
 * @author zhaozhou
 */
public class ArrayContainer {
    private static final int[] array = new int[]{1, 2, 3, 4, 5};
    private int cursor = 0;
    public Iterator<Integer> iterator() {
        return new Itr();
    }
    private class Itr implements Iterator<Integer> {
        @Override
        public boolean hasNext() {
            return cursor != array.length;
        }
        @Override
        public Integer next() {
            if (cursor > array.length) {
                throw new NoSuchElementException();
            }
            Integer result = array[cursor];
            cursor = cursor + 1;
            return result;
        }
    }
    public static void main(String[] args) {
        ArrayContainer numbers = new ArrayContainer();
        Iterator<Integer> iterator = numbers.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
