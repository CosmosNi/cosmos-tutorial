package com.cosmos.base.juc.thread;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.juc.thread
 * @ClassName: Demo13
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/3 16:45
 * @Version: 1.0
 */
public class Demo13 {
    static String value = "";
    static String lock = new String("123");

    public void getValue() throws InterruptedException {
        synchronized (lock) {
            if (value == "") {
                lock.wait();
            }

            System.out.println("获取值" + value);
            value = "";
            lock.notify();
        }
    }


    public void setValue() throws InterruptedException {
        synchronized (lock) {
            if (value != "") {
                lock.wait();
            }
            value = System.currentTimeMillis() + "";
            System.out.println("设置值为" + value);
            lock.notify();
        }

    }

    public static void main(String[] args) {
        Demo13 demo13 = new Demo13();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {

                try {
                    Thread.sleep(1000);
                    demo13.setValue();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();

            new Thread(() -> {

                try {
                    Thread.sleep(1000);
                    demo13.getValue();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();
        }


    }


//    public static void main(String[] args) {
//        Demo13 demo13 = new Demo13();
//
//        new Thread(() -> {
//            while (true) {
//                try {
//                    Thread.sleep(1000);
//                    demo13.setValue();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//        new Thread(() -> {
//            while (true) {
//                try {
//                    Thread.sleep(1000);
//                    demo13.getValue();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }

}
