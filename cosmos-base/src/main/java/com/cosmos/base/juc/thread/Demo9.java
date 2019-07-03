package com.cosmos.base.juc.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.juc.thread
 * @ClassName: Demo9
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/3 14:09
 * @Version: 1.0
 */
public class Demo9 {
    //保证可见性，不保证原子性
    volatile int count = 0;

    public void test() {
        for (int i = 0; i < 100; i++) {
            count++;
        }

    }

    public int getCount() {
        return count;
    }


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        Demo9 demo9 = new Demo9();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                demo9.test();
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println(demo9.getCount());
    }
}
