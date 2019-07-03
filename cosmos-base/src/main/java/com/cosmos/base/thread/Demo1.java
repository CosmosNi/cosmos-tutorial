package com.cosmos.base.thread;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.thread
 * @ClassName: Demo1
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/2 23:20
 * @Version: 1.0
 */
public class Demo1 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println(i);
            }
        });
        thread.start();
        Thread.sleep(1000);
        //测试当前线程是否已经是中断状态，执行后具有将状态标志置清除为false的功能
        thread.interrupt();
        //测试线程Thread对象是否已经是中断状态，但不清除状态标记
        System.out.println(thread.isInterrupted());


    }
}
