package com.cosmos.base.juc;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.juc
 * @ClassName: ThreadPoolTest
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/8 14:46
 * @Version: 1.0
 */
public class ThreadPoolTest {

    public static ExecutorService initThreadPool() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("test-Thread-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<>(), namedThreadFactory) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行" + Thread.currentThread().getName());
            }

            @Override
            protected void terminated() {
                System.out.println("线程退出" + Thread.currentThread().getName());
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("线程执行完成" + Thread.currentThread().getName());
            }
        };

        return pool;
    }

    public static void main(String[] args) {

        ExecutorService executorService = ThreadPoolTest.initThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println(Runtime.getRuntime().availableProcessors());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
    }
}
