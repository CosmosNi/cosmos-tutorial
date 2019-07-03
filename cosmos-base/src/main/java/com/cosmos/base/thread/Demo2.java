package com.cosmos.base.thread;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.thread
 * @ClassName: Demo2
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/3 9:27
 * @Version: 1.0
 */
public class Demo2 {
    public static void main(String[] args) {
        new Thread(() -> {
            int count = 1;
            long start = System.currentTimeMillis();
            for (int i = 0; i < 100000; i++) {
                count += i;
            }
            System.out.println("执行时长：" + (System.currentTimeMillis() - start));
        }).start();
        new Thread(() -> {
            int count = 1;
            long start = System.currentTimeMillis();
            for (int i = 0; i < 100000; i++) {
                Thread.yield();
                count += i;
            }
            System.out.println("执行时长：" + (System.currentTimeMillis() - start));
        }).start();
    }
}
