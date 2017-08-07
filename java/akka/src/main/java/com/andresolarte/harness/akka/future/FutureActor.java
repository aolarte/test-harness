package com.andresolarte.harness.akka.future;

import akka.actor.UntypedActor;

public class FutureActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Long) {
            Long l=(Long)message;
            getSender().tell(l*2,null);
        } else {
            unhandled(message);
        }
    }
}
