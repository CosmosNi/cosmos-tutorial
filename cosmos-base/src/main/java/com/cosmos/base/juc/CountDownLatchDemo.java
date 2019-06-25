package com.cosmos.base.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.juc
 * @ClassName: CountDownLatchDemo
 * @Author: keda
 * @Description: \
 * 闭锁：
 * * 1.确保一个计算不被执行，直到它需要的资源初始化。
 * * 2.确保一个服务不会开始，直到它依赖的其他服务都已经开始
 * * 3.等待，直到活动的所有部分都为继续处理做好充分准备
 * *
 * * CountDownLatch是一个灵活的闭锁实现。允许一个或多个线程等待一个事件集的发生。闭锁状态包括一个计数器，初始化为一个整数
 * * 用来表现需要等待的事件数。countDown方法针对计数器做减操作，表示一个事件已经发生了，
 * * 而await方法等待计数器达到零，此时所有需要等待的事件都已经发生。
 * * 如果计数器入口时值为非零，await会一直堵塞直到计数器为零，或者等待线程中断以及超时。
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
