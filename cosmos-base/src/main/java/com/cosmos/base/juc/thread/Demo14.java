package com.cosmos.base.juc.thread;

/**
 * join
 */
public class Demo14 {


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("开始执行子线程");
            try {
                Thread.sleep(5000);
                System.out.println("子线程执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread.join();
        System.out.println("任务完成");
    }
}
