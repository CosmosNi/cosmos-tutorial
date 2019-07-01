package com.cosmos.base.juc;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.juc
 * @ClassName: ExchangerTest
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/1 10:59
 * @Version: 1.0
 */
public class ExchangerTest {
    private ExecutorService service = Executors.newFixedThreadPool(10);

    public void test() {
        Exchanger<String> exchanger = new Exchanger();
        service.execute(() -> {

            try {
                String data = "交换对象1";

                String result = exchanger.exchange(data);
                System.out.println("交换前对象：" + data + "   交换后对象：" + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        service.execute(() -> {

            try {
                String data = "交换对象2";
                String result = exchanger.exchange(data);
                System.out.println("交换前对象：" + data + "   交换后对象：" + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        service.shutdown();
    }

    public static void main(String[] args) {
        ExchangerTest exchangerTest = new ExchangerTest();
        exchangerTest.test();
    }
}
