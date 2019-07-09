package com.cosmos.base.juc.disruptor;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ThreadFactory;

/**
 * 无锁模式下的生产者消费者
 */
public class ProduceDriver {
    public static void main(String[] args) throws InterruptedException {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("test-Thread-%d").build();

        //构造所有缓存区的实例，用来Disruptor预先分配空间
        PCDataFactory factory = new PCDataFactory();
        //设置缓冲区
        int bufferSize = 1024;

        Disruptor<PCData> disruptor = new Disruptor<>(factory, bufferSize, namedThreadFactory, ProducerType.MULTI, new BlockingWaitStrategy());
        //设置消费者
        disruptor.handleEventsWithWorkerPool(new Consumer("A"), new Consumer("B"), new Consumer("C"), new Consumer("D"));
        disruptor.start();

        //将生产者数据写入缓冲区
        RingBuffer<PCData> ringBuffer = disruptor.getRingBuffer();
        Producer producer = new Producer(ringBuffer);
        for (long i = 0; ; i++) {
            producer.pushData(i, "name" + i);
            Thread.sleep(100);
            System.out.println("add data：" + i);
        }
    }
}
