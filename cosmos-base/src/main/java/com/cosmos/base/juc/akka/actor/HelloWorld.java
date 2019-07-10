package com.cosmos.base.juc.akka.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.juc.akka.actor
 * @ClassName: HelloWorld
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/10 9:03
 * @Version: 1.0
 */
public class HelloWorld extends UntypedAbstractActor {
    ActorRef greeter;

    @Override
    public void preStart() throws Exception {
        super.preStart();
        //创建消费者
        greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
        System.out.println("Greeter Actor Path" + greeter.path());
        //greeter.tell(Greeter.Msg.GREET, getSelf());
    }

    @Override
    public void postStop() throws Exception {
        super.postStop();
        System.out.println("server stop ");
    }

    @Override
    public void onReceive(Object o) throws Throwable {
        if (o == Greeter.Msg.DONE) {
            //发送消息
            greeter.tell(Greeter.Msg.GREET, getSelf());
            //关闭通道
            getContext().stop(getSelf());
        } else {
            unhandled(o);
        }
    }
}
