package com.cosmos.base.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.juc
 * @ClassName: SemaphoreDemo
 * @Author: keda
 * @Description: 计数信号量，用来控制能够同时访问某特定资源的活动的数量
 * * 计数信号量可以用来实现资源池或者给一个容器限定边界。
 * * 一个Semaphore管理一个有效的许可，许可的除湿量通过构造函数传递给semaphore
 * * 活动能够获得许可（只要还有剩余许可），并在使用之后释放许可，如果没有可用的许可
 * * 则acquire会被堵塞，直到有可用的为止
 * * 类似数据库连接池
 * @Date: 2019/6/25 14:10
 * @Version: 1.0
 */
public class SemaphoreDemo {

    private static final ExecutorService executor = Executors.newFixedThreadPool(30);
    //红绿灯每次只能过5人
    private static final Semaphore semaphore = new Semaphore(5);
    private static final AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void road() {
        for (int i = 0; i < 30; i++) {
            executor.execute(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "获取许可准备过马路！");
                    Thread.sleep(1000 * atomicInteger.getAndIncrement());
                    System.out.println(Thread.currentThread().getName() + "过完马路！开始释放许可");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
        }
        executor.shutdown();
    }

    public static void main(String[] args) {
        SemaphoreDemo.road();
    }
}
