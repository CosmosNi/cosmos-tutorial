package com.cosmos.base.juc.thread;

/**
 * wait的线程被打断会抛出异常
 */
public class Demo12 {

    public static void main(String[] args) {
        Object o = new Object();

        Thread t = new Thread(() -> {
            synchronized (o) {
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();
        t.interrupt();
    }
}
