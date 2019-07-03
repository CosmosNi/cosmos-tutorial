package com.cosmos.base.juc.thread;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.juc.thread
 * @ClassName: Demo3
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/3 10:04
 * @Version: 1.0
 */
public class Demo3 {

    public void test() {
        String a = "";
        long start = System.currentTimeMillis();
        int count = 0;
        synchronized (this) {

            count++;
        }
        long end = System.currentTimeMillis();
        synchronized (a) {
            count++;
        }
        System.out.println("this:" + Thread.currentThread().getName() + "   " + (end - start) + "  " + "a:" + Thread.currentThread().getName() + "   " + (System.currentTimeMillis() - end));

    }

    public static void main(String[] args) {
        Demo3 demo3 = new Demo3();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                demo3.test();
            }).start();
        }
    }
}
