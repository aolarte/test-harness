package com.andresolarte.harness.akka.future;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;
import com.andresolarte.harness.akka.pojo.HelloWorld;
import scala.concurrent.Await;
import scala.concurrent.duration.Duration;
import scala.concurrent.Future;

public class FutureTest {
    public static void main(String... args) {

        ActorSystem system = ActorSystem.create("Hello");

        ActorRef actor = system.actorOf(Props.create(FutureActor.class), "Future World");
        Timeout timeout = new Timeout(Duration.create(60, "seconds"));

        Long msg=100L;
        Future<Object> future = Patterns.ask(actor, msg, timeout);
        try {
            Long result = (Long) Await.result(future, timeout.duration());
            System.out.println("Result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        system.shutdown();
    }
}
