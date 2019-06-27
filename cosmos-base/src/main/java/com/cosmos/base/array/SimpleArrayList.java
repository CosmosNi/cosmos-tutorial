package com.cosmos.base.array;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.array
 * @ClassName: SimpleArrayList
 * @Author: keda
 * @Description: 一个简单的ArrayList
 * @Date: 2019/6/17 22:17
 * @Version: 1.0
 */
public final class SimpleArrayList<T> {
    /**
     * 数组的初始容量
     */
    private static final int DEFAULT_CAPACITY = 10;

    Object[] elementData;

    /**
     * 无数据
     */
    private Object[] EMPTY_ELEMENT = {};

    /**
     * 存储数量
     */
    private AtomicInteger size = new AtomicInteger(0);

    public SimpleArrayList() {
        this.elementData = EMPTY_ELEMENT;
    }

    public SimpleArrayList(int initialCapacity) {
        if (initialCapacity == 0)
            this.elementData = EMPTY_ELEMENT;
        if (initialCapacity > 1)
            this.elementData = new Object[initialCapacity];
        if (initialCapacity < 0)
            throw new IllegalArgumentException("初始容量不可小于0");
    }

    public void add(T t) {
        ensureCapacityInternal(size.get() + 1);
        elementData[size.getAndIncrement()] = t;
    }

    public void add(int index, T t) {
        if (index > size.getAndIncrement() || index < 0)
            throw new IndexOutOfBoundsException();
        System.arraycopy(elementData, index, elementData, index + 1, size.get() - index);
        elementData[index] = t;
        size.getAndIncrement();
    }

    public void remove(T t) {
        if (t == null) {
            for (int i = 0; i < size.get(); i++) {
                if (elementData[i] == null) {
                    remove(i);
                }
            }
        } else {
            for (int i = 0; i < size.get(); i++) {
                if (t.equals(elementData[i])) {
                    remove(i);
                }
            }
        }
    }

    public void remove(int index) {
        if (index >= size.get())
            throw new IndexOutOfBoundsException();
        System.arraycopy(elementData, index, elementData, index - 1, size.get() - index - 1);
        elementData[size.decrementAndGet()] = null;
    }


    private void ensureCapacityInternal(int minSize) {
        if (elementData == EMPTY_ELEMENT) {
            minSize = Math.max(minSize, DEFAULT_CAPACITY);
        }
        //动态扩容
        if (minSize > elementData.length) {
            int oldCapacity = elementData.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            if (newCapacity == 0)
                newCapacity = minSize;
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    public int size() {
        return size.get();
    }


    public static void main(String[] args) {
        SimpleArrayList<Integer> list = new SimpleArrayList<>();
        for (int i = 0; i < 2000; i++) {
            list.add(i);
        }
        list.remove(10);
        list.remove(1234);
        System.out.println("size:" + list.size());
    }
}
