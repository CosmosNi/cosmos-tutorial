package com.cosmos.base.array;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.array
 * @ClassName: SimpleArray
 * @Author: keda
 * @Description: 此demo主要用于展示基础的Array的增删改
 * @Date: 2019/6/17 21:36
 * @Version: 1.0
 */
public final class SimpleArray<V> {
    /**
     * 存放对象
     **/
    private Object[] element;

    /**
     * 元素数量
     */
    private AtomicInteger size = new AtomicInteger(0);

    /**
     * 数组容量
     */
    private int capacity;

    /**
     * 初始化数组
     *
     * @param capacity
     */
    public SimpleArray(int capacity) {
        if (capacity < 0)
            throw new IllegalArgumentException("数组容量必须大于0");

        this.capacity = capacity;
        this.element = new Object[capacity];
    }

    /**
     * 新增元素
     *
     * @param v
     */
    public void add(V v) {
        if (v == null)
            throw new NullPointerException();
        if (size.get() > capacity - 1)
            throw new ArrayIndexOutOfBoundsException("数组容量已满");

        element[size.getAndIncrement()] = v;
    }

    /**
     * 移除某个元素
     *
     * @param v
     * @return
     */
    public boolean remove(V v) {
        if (v == null)
            throw new NullPointerException();
        for (int i = 0; i < size.get(); i++) {
            if (element[i].equals(v)) {
                synchronized (element) {
                    while (i < size.get() - 1) {
                        element[i] = element[++i];
                    }
                    element[size.decrementAndGet()] = null;
                }
                return true;
            }

        }
        return false;
    }

    /**
     * 查看元素是否存在
     *
     * @param v
     * @return
     */
    public boolean isExist(V v) {
        if (v == null)
            throw new NullPointerException();
        for (int i = 0; i < size.get(); i++) {
            if (element[i].equals(v)) {
                return true;
            }
        }
        return false;
    }

    /***
     * 根据索引获取元素
     * @param index
     * @return
     */
    public V get(int index) {
        if (index >= capacity) {
            throw new IndexOutOfBoundsException();
        }
        return (V) element[index];
    }


    public static void main(String[] args) {

        SimpleArray<Integer> stringArray = new SimpleArray<>(10);
        for (int i = 0; i < 9; i++) {
            stringArray.add(i);
        }
        Arrays.asList(stringArray.element).stream().filter(element -> element != null).forEach(System.out::print);

        stringArray.remove(7);

        System.out.println();
        Arrays.asList(stringArray.element).stream().filter(element -> element != null).forEach(System.out::print);
        System.out.println();

        System.out.println(stringArray.isExist(7));
        System.out.println(stringArray.isExist(6));

        System.out.println(stringArray.get(6));
    }
}
