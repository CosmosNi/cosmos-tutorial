package com.cosmos.base.juc.guava;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.juc.guava
 * @ClassName: RateLimiterDemo
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/8 13:44
 * @Version: 1.0
 */
public class RateLimiterDemo {

    static RateLimiter limiter = RateLimiter.create(1);

    public static void acquire() {
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                limiter.acquire();
                System.out.println(System.currentTimeMillis());
            }).start();
        }
    }

    public static void tryAcquire() {
        for (int i = 0; i < 50; i++) {
            //当没有令牌则请求实效
            if (!limiter.tryAcquire()) {
                continue;
            }
            new Thread(() -> {
                System.out.println(System.currentTimeMillis());
            }).start();

        }
    }

    public static void main(String[] args) {
        RateLimiterDemo.tryAcquire();
    }
}
