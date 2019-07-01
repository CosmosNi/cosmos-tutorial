package com.cosmos.base.queue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 *通常情况下，我们会在线程之间进行通信。
 * 一个线程产生数据，另一个线程则取出数据进行相关的业务逻辑处理。类似于生产者或消费者。
 * 在软件设计中通常会将各个模块做的尽量独立，基于这种情况，生产者可以将数据写入到一个中间数据结构中，
 * 而消费者可以从这个中间数据结构中取出数据进行消费，实现了软件间的松耦合。

 */
public class ArrayBlockingQueueTest {

    private ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(10);

    public void put() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        arrayBlockingQueue.put(Math.random());
        System.out.println("写入元素所花时长" + (System.currentTimeMillis() - startTime));
    }

    public void remove() throws InterruptedException {
        long startTime = System.currentTimeMillis();

        arrayBlockingQueue.take();
        System.out.println("移除元素所花时长" + (System.currentTimeMillis() - startTime));
    }

    public static void main(String[] args) {
        ArrayBlockingQueueTest arrayBlockingQueueTest = new ArrayBlockingQueueTest();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(10);
                    arrayBlockingQueueTest.put();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            new Thread(() -> {
                try {
                    Thread.sleep(100);
                    arrayBlockingQueueTest.remove();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
