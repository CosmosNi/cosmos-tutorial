package com.cosmos.base.juc.disruptor;

import com.lmax.disruptor.WorkHandler;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.juc.disruptor
 * @ClassName: Consumer
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/9 15:16
 * @Version: 1.0
 */
public class Consumer implements WorkHandler<PCData> {
    private String name;

    public Consumer(String name) {
        this.name = name;
    }

    @Override
    public void onEvent(PCData pcData) {
        System.out.println(Thread.currentThread().getName() + ":" + pcData.toString() + ": " + name);
    }
}
