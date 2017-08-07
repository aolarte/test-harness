package com.andresolarte.harness.akka.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.pattern.Patterns;
import akka.util.Timeout;
import com.andresolarte.harness.akka.pojo.DuplicatorMessage;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;


public class DuplicatorWorker extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof DuplicatorMessage) {
            DuplicatorMessage duplicatorMessage=(DuplicatorMessage)message;
            ActorRef actorRef=this.getContext().actorOf(Props.create(DuplicatorWorker.class));
            DuplicatorMessage msg2=new DuplicatorMessage();
            msg2.countLeft=duplicatorMessage.countLeft-1;
            msg2.value=duplicatorMessage.value*2;
            if (msg2.countLeft>0) {
                Timeout timeout = new Timeout(Duration.create(5, "seconds"));
                Future<Object> future = Patterns.ask(actorRef, msg2, timeout);
                DuplicatorMessage result = (DuplicatorMessage) Await.result(future, timeout.duration());
            }
        } else {
            unhandled(message);
        }
    }
}
