package com.cosmos.base.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.thread
 * @ClassName: Demo4
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/3 10:27
 * @Version: 1.0
 */
public class Demo4 {

    private List<String> list = new ArrayList();

    public synchronized void add(String element) {
        list.add(element);
    }

    public synchronized int getSize() {
        return list.size();
    }

    public static class Service {
        //方法未同步，导致脏读,两根线程同时到达此处时，都会进行写入
//        public Demo4 addList(Demo4 demo4, String data) {
//            //只能保存一个元素
//            if (demo4.getSize() < 1) {
//                try {
//                    Thread.sleep(1000);
//                    demo4.add(data);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//            return demo4;
//        }
        public Demo4 addList(Demo4 demo4, String data) {
            //只能保存一个元素
            synchronized (demo4) {
                if (demo4.getSize() < 1) {
                    try {
                        Thread.sleep(1000);
                        demo4.add(data);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
            return demo4;
        }
    }

    public static class Thread1 extends Thread {
        private Demo4 demo4;

        public Thread1(Demo4 demo4) {
            this.demo4 = demo4;
        }

        @Override
        public void run() {
            Service service = new Service();
            service.addList(demo4, "A");
        }
    }

    public static class Thread2 extends Thread {
        private Demo4 demo4;

        public Thread2(Demo4 demo4) {
            this.demo4 = demo4;
        }

        @Override
        public void run() {
            Service service = new Service();
            service.addList(demo4, "B");
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Demo4 demo4 = new Demo4();
        Thread1 thread1 = new Thread1(demo4);
        thread1.start();
        Thread2 thread2 = new Thread2(demo4);
        thread2.start();
        Thread.sleep(6000);
        System.out.println(demo4.getSize());
    }
}
