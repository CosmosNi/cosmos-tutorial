package com.cosmos.base.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.juc
 * @ClassName: ConditionTest
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/29 23:24
 * @Version: 1.0
 */
public class ConditionTest {


    public static Object[] items = new Object[5];

    private ReentrantLock reentrantLock = new ReentrantLock();

    //条件对象
    private Condition empty = reentrantLock.newCondition();

    private Condition full = reentrantLock.newCondition();

    private int count = 0;

    private int put = 0;

    private int take = 0;

    public void put() {
        reentrantLock.lock();
        try {
            while (count == items.length) {
                System.out.println("写线程等待");
                full.await();
            }

            count++;
            put++;
            //线程唤醒
            empty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    public void take() {
        reentrantLock.lock();
        try {

            while (count == 0) {
                System.out.println("读线程等待");
                empty.await();
            }
            count--;
            take++;
            //线程唤醒
            full.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }


    public static void main(String[] args) {
        ConditionTest conditionTest = new ConditionTest();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1);
                    conditionTest.put();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();
            new Thread(() -> {
                try {
                    Thread.sleep(10);
                    conditionTest.take();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
