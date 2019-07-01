package com.cosmos.base.juc;

import java.util.concurrent.Phaser;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.juc
 * @ClassName: PhaserTest
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/1 11:07
 * @Version: 1.0
 */
public class PhaserTest {


    public static class Task implements Runnable {

        private Phaser phaser;

        public Task(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "准备开启第一批次任务！");
            //计数不足时，线程呈阻塞状态，不能继续向下运行
            phaser.arriveAndAwaitAdvance();

            System.out.println(Thread.currentThread().getName() + "准备开启第二批次任务！");
            phaser.arriveAndAwaitAdvance();

            System.out.println(Thread.currentThread().getName() + "准备开启第三批次任务！");

        }

    }


    public static void main(String[] args) throws InterruptedException {
        Phaser phaser = new Phaser(10);
        //注册主线程
        phaser.register();
        for (int i = 0; i < 10; i++) {

            new Thread(new Task(phaser)).start();
        }
        Thread.sleep(5000);
        //使当前线程退出，并且是parties值减1,取消注册
        phaser.arriveAndDeregister();
        while (!phaser.isTerminated()) {

        }
        System.out.println("所有任务均已完成");
    }
}
