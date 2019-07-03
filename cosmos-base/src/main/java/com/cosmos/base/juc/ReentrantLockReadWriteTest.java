package com.cosmos.base.juc;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 多根线程能同时间进行读，但只能有一根线程进行写
 */
public class ReentrantLockReadWriteTest {
    private ReentrantReadWriteLock reentrantLockReadWrite = new ReentrantReadWriteLock();

    public void read() {
        try {
            reentrantLockReadWrite.readLock().lock();
            System.out.println(Thread.currentThread().getName() + "-------读线程--------");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLockReadWrite.readLock().unlock();
        }
    }

    public void write() {
        try {
            reentrantLockReadWrite.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + "-------写线程--------");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLockReadWrite.writeLock().unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockReadWriteTest rrw = new ReentrantLockReadWriteTest();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                rrw.read();
                rrw.write();
            }).start();
        }

    }
}
