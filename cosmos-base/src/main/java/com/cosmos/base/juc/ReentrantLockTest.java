package com.cosmos.base.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;


public class ReentrantLockTest {

    private int size = 0;

    private int unSafeSize = 0;

    public ReentrantLock reentrantLock = new ReentrantLock();////参数默认false，非公平锁（线程抢占，吞吐量大）


    public void add() {
        try {
            if (reentrantLock.tryLock()) {
                size++;
                reentrantLock.unlock();
            }else{
                System.out.println("没获取锁");
            }

        } finally {
            //reentrantLock.unlock();

        }
    }

    public void testInterrupt() {
        final ReentrantLock reentrantLock = this.reentrantLock;
        try {
            //使用该方法，线程被打断时会被唤醒
            reentrantLock.lockInterruptibly();

        } catch (InterruptedException e) {
            System.out.println("线程被打断");
        } finally {
            reentrantLock.unlock();
        }
    }


    private void unSafeAdd() {
        unSafeSize++;
    }

    public int getSize() {
        return size;
    }

    public int getUnSafeSize() {
        return unSafeSize;
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
        ExecutorService service = Executors.newFixedThreadPool(30);
        int count = 10000;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            service.execute(() -> {
                reentrantLockTest.add();
                reentrantLockTest.unSafeAdd();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();

        System.out.println("线程安全：" + reentrantLockTest.getSize());

        System.out.println("线程不安全：" + reentrantLockTest.getUnSafeSize());

        service.shutdown();

//        reentrantLockTest.reentrantLock.lock();
//
//        Thread t = new Thread(() -> reentrantLockTest.testInterrupt());
//        t.start();
//        Thread.sleep(1000);
//        t.interrupt();

    }
}
