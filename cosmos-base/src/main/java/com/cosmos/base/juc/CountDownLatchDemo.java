package com.cosmos.base.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.juc
 * @ClassName: CountDownLatchDemo
 * @Author: keda
 * @Description: \
 * @Date: 2019/6/25 13:49
 * @Version: 1.0
 */
public class CountDownLatchDemo {

    public static void task(int nThread) throws InterruptedException {
        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(nThread);
        for (int i = 0; i < nThread; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "等待执行任务！");
                    start.await();
                    System.out.println(Thread.currentThread().getName() + "开始执行任务！");
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + "任务执行完毕！");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    end.countDown();
                }
            }).start();
        }
        Thread.sleep(4000);
        System.out.println("主线程准备开启阀门");

        long beginTime = System.currentTimeMillis();
        start.countDown();
        System.out.println("主线程等待多个任务执行");
        try {
            end.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            System.out.println("任务执行完毕!总耗时：" + (System.currentTimeMillis() - beginTime));
        }

    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatchDemo.task(10);
    }

}
