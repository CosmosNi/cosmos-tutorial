package com.cosmos.base.juc.thread;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.juc.thread
 * @ClassName: Demo5
 * @Author: keda
 * @Description: 死锁的案例
 * @Date: 2019/7/3 13:16
 * @Version: 1.0
 */
public class Demo5 {

    private Object lock1 = new Object();
    private Object lock2 = new Object();


    public void test1() {
        synchronized (lock1) {
            System.out.println("test1: 开始执行第一次");
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2) {
                System.out.println("test1: 开始执行第二次");
            }
        }


    }

    public void test2() {
        synchronized (lock2) {
            System.out.println("test2: 开始执行第一次");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock1) {
                System.out.println("test2: 开始执行第二次");
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Demo5 demo5 = new Demo5();
        new Thread(() -> {
            demo5.test1();
        }).start();
        Thread.sleep(100);
        new Thread(() -> {
            demo5.test2();
        }).start();
    }
}
