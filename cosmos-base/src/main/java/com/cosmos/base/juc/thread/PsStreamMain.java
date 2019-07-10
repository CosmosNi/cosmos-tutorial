package com.cosmos.base.juc.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 流水线算法(B+C)*B/2
 */
public class PsStreamMain {

    public static void main(String[] args) {
        new Thread(new Plus()).start();
        new Thread(new Multiply()).start();
        new Thread(new Div()).start();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Msg msg = new Msg();
                msg.i = i;
                msg.j = j;
                Plus.blockingQueue.add(msg);
            }
        }
    }


    public static class Plus implements Runnable {
        public static BlockingQueue<Msg> blockingQueue = new LinkedBlockingQueue<>();

        @Override
        public void run() {
            while (true) {
                try {
                    Msg msg = blockingQueue.take();
                    msg.j = msg.i + msg.j;
                    Multiply.blockingQueue.add(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static class Multiply implements Runnable {
        public static BlockingQueue<Msg> blockingQueue = new LinkedBlockingQueue<>();

        @Override
        public void run() {
            while (true) {
                try {
                    Msg msg = blockingQueue.take();
                    msg.j = msg.i * msg.j;
                    Div.blockingQueue.add(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    public static class Div implements Runnable {
        public static BlockingQueue<Msg> blockingQueue = new LinkedBlockingQueue<>();

        @Override
        public void run() {
            while (true) {
                try {
                    Msg msg = blockingQueue.take();
                    System.out.println("result:" + (double) msg.j / 2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    public static class Msg {
        public double i;
        private double j;
    }
}
