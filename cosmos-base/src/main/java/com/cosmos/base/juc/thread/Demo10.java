package com.cosmos.base.juc.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.juc.thread
 * @ClassName: Demo10
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/3 14:18
 * @Version: 1.0
 */
public class Demo10 {
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public void test() {
        System.out.println(atomicInteger.getAndIncrement());
    }


    public static void main(String[] args) {
        Demo10 demo10 = new Demo10();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                demo10.test();
            }).start();
        }
    }
}
