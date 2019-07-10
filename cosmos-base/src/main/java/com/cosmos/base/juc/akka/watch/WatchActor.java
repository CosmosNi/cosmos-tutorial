package com.cosmos.base.juc.akka.watch;

import akka.actor.ActorRef;
import akka.actor.Terminated;
import akka.actor.UntypedAbstractActor;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.juc.akka.watch
 * @ClassName: WatchActor
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/10 9:37
 * @Version: 1.0
 */
public class WatchActor extends UntypedAbstractActor {

    public WatchActor(ActorRef ref) {
        getContext().watch(ref);
    }

    @Override
    public void onReceive(Object msg) throws Throwable {
        if (msg instanceof Terminated) {

            System.out.println(String.format("%s has terminated,shutting down system", ((Terminated) msg).getActor().path()));
        }
    }
}
