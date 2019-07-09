package com.cosmos.base.jms;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.jms
 * @ClassName: EventListener
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/9 11:17
 * @Version: 1.0
 */
public class EventListener {
    public int lastMessage = 0;

    @Subscribe
    public void listen(TestEvent event) {
        lastMessage = event.getMessage();
        System.out.println("Message:" + lastMessage);
    }

    public int getLastMessage() {
        return lastMessage;
    }

    public static void main(String[] args) {
        EventBus eventBus = new EventBus("test");
        EventListener listener = new EventListener();

        eventBus.register(listener);

        eventBus.post(new TestEvent(200));
        eventBus.post(new TestEvent(300));
        eventBus.post(new TestEvent(400));

    }
}
