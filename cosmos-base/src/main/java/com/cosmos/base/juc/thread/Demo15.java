package com.cosmos.base.juc.thread;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ArrayList
 */
public class Demo15 {


    public static void main(String[] args) throws InterruptedException {

        List<Integer> arrayList = new CopyOnWriteArrayList();
        Thread t = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                arrayList.add(i);
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                arrayList.add(i);
            }
        });
        t.start();
        t2.start();
        t.join();
        t2.join();
        System.out.println(arrayList.size());

    }
}
