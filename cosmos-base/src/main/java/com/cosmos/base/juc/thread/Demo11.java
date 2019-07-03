package com.cosmos.base.juc.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * wait方法可以使处于临界区内的线程进入等待状态，同时释放被同步对象的锁
 * notify操作可以唤醒一个因调用wait操作而处于堵塞状态的线程，使其进入就绪状态。
 * notifyAll可以使所有正在等待队列中等待同一共享资源的全部线程从等待状态退出，进入可运行状态。
 */
public class Demo11 {
    private Object object = new Object();

    private List<Integer> list = new ArrayList<>();

    public void notifyObject() throws InterruptedException {
        synchronized (object) {
            for (int i = 0; i < 10; i++) {
                list.add(i);
                if (list.size() == 5) {
                    System.out.println("唤醒线程");
                    object.notifyAll();
                }
                System.out.println("输出：" + i);
                Thread.sleep(1000);
            }

        }
    }

    public void waitObject() throws InterruptedException {
        synchronized (object) {
            if (list.size() != 5) {
                System.out.println("线程等待！");
                object.wait();
                System.out.println("线程被唤醒");
            }
        }
    }

    public static void main(String[] args) {
        Demo11 demo11 = new Demo11();
        new Thread(() -> {
            try {
                demo11.waitObject();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                demo11.notifyObject();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
