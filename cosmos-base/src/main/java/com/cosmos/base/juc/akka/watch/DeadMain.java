package com.cosmos.base.juc.akka.watch;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.juc.akka.watch
 * @ClassName: DeadMain
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/10 9:43
 * @Version: 1.0
 */
public class DeadMain {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("deadwatch", ConfigFactory.load("sample.conf"));
        ActorRef worker = system.actorOf(Props.create(MyWorker.class), "worker");
        //注册监听
        system.actorOf(Props.create(WatchActor.class, worker), "watcher");
        worker.tell(MyWorker.Msg.WORKING, ActorRef.noSender());
        worker.tell(MyWorker.Msg.DONE, ActorRef.noSender());
        worker.tell(PoisonPill.getInstance(), ActorRef.noSender());

    }
}
