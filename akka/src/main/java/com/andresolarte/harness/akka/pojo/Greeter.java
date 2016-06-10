package com.andresolarte.harness.akka.pojo;

import akka.actor.UntypedActor;

public class Greeter extends UntypedActor {

    public static enum Msg {
        GREET, DONE;
    }

    @Override
    public void onReceive(Object msg) {
        if (msg == Msg.GREET) {
            System.out.println("Hello World 1 !");
            getSender().tell(Msg.DONE, getSelf());
        } else
            unhandled(msg);
    }

}
