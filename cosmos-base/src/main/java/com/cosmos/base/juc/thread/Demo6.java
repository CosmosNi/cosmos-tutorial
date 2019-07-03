package com.cosmos.base.juc.thread;

/**
 * 对象变更会导致持有不同锁，也就造成了异步
 */
public class Demo6 {
    private String lock = "123";

    public void test() throws InterruptedException {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis());
            Thread.sleep(50);
            lock = "456";
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Demo6 demo6 = new Demo6();
        new Thread(() -> {
            try {
                demo6.test();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        //导致锁更改,第一根线程持有123，第二根线程持有456
       Thread.sleep(100);
        new Thread(() -> {
            try {
                demo6.test();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
