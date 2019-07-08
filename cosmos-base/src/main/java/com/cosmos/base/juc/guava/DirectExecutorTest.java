package com.cosmos.base.juc.guava;

import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Executor;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.juc.guava
 * @ClassName: DirectExecutorTest
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/8 15:36
 * @Version: 1.0
 */
public class DirectExecutorTest {

    public static void main(String[] args) {
        Executor executor = MoreExecutors.directExecutor();
        executor.execute(()->{
            System.out.println(Thread.currentThread().getName());
        });
    }
}
