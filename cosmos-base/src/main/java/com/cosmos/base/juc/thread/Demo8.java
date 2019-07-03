package com.cosmos.base.juc.thread;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.juc.thread
 * @ClassName: Demo8
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/3 13:51
 * @Version: 1.0
 */
public class Demo8 {

    private boolean stop = true;

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public void test() throws InterruptedException {
        while (stop) {
            System.out.println("执行方法：" + Thread.currentThread());
            Thread.sleep(1000);
        }
        System.out.println("线程停止了");
    }

    public static void main(String[] args) throws InterruptedException {
        Demo8 demo8 = new Demo8();
//        demo8.test();
//


        new Thread(() -> {
            try {
                demo8.test();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(50);
        demo8.setStop(false);
        Thread.sleep(5000);
    }
}
