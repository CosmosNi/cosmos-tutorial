package com.cosmos.base.juc.akka.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.juc.akka.actor
 * @ClassName: HelloMainSimple
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/10 9:07
 * @Version: 1.0
 */
public class HelloMainSimple {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("test", ConfigFactory.load("sample.conf"));
        ActorRef hello = system.actorOf(Props.create(HelloWorld.class), "HelloWord");
        hello.tell(Greeter.Msg.DONE, ActorRef.noSender());
        System.out.println("HelloWorld Actor Path" + hello.path());
    }
}
