package com.cosmos.base.juc.akka.actor;


import akka.actor.UntypedAbstractActor;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.juc.akka.actor
 * @ClassName: Greeter
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/9 23:17
 * @Version: 1.0
 */
public class Greeter extends UntypedAbstractActor {
    public static enum Msg {
        GREET, DONE;
    }

    @Override
    public void onReceive(Object o) throws Throwable {
        if (o == Msg.GREET) {
            System.out.println("Hello World");
            this.getSender().tell(Msg.DONE, getSelf());
        }else{
            this.unhandled(o);
        }
    }
}
