package com.cosmos.base.juc.akka.watch;

import akka.actor.UntypedAbstractActor;
import com.cosmos.base.juc.akka.actor.Greeter;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.juc.akka.watch
 * @ClassName: MyWorker
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/10 9:44
 * @Version: 1.0
 */
public class MyWorker extends UntypedAbstractActor {

    public static enum Msg {
        WORKING, DONE, CLOSE;
    }

    @Override
    public void preStart() throws Exception {
        super.preStart();
        //创建消费者
        System.out.println("worker start ");
    }

    @Override
    public void postStop() throws Exception {
        super.postStop();
        System.out.println("server stop ");
    }

    @Override
    public void onReceive(Object o) throws Throwable {
        if (o == Greeter.Msg.GREET) {
            System.out.println("I am Working");
        }
        if (o == Msg.DONE) {
            System.out.println("stop Working");
        }
        if (o == Msg.CLOSE) {
            System.out.println("shutdown");
            getSender().tell(Msg.CLOSE, getSelf());

        }
    }
}
