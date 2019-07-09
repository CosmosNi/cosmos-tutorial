package com.cosmos.base.jms;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.jms
 * @ClassName: TestEvent
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/9 11:16
 * @Version: 1.0
 */
public class TestEvent {
    private final int message;

    public TestEvent(int message) {
        this.message = message;
        System.out.println("event message:" + message);
    }

    public int getMessage() {
        return message;
    }
}
