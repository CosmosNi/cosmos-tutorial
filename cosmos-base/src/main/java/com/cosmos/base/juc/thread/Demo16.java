package com.cosmos.base.juc.thread;

import java.util.HashMap;
import java.util.Map;

/**
 * hashmap
 */
public class Demo16 {

    static Map<String, String> map = new HashMap<>();

    public static class addThread implements Runnable {
        int start = 0;

        public addThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000000; i += 2) {
                map.put(Integer.toString(i), Integer.toBinaryString(i));
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new addThread(0));
        Thread t2 = new Thread(new addThread(1));
        t.start();
        t2.start();
        t.join();
        t2.join();
        System.out.println(map.size());

        Thread.sleep(100000);
    }
}
