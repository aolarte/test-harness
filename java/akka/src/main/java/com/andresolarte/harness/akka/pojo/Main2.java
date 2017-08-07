package com.andresolarte.harness.akka.pojo;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main2 {

    public static void main(String[] args) {
        System.out.println("Creating System");

        ActorSystem system = ActorSystem.create("Hello");
        ActorRef a = system.actorOf(Props.create(HelloWorld.class), "helloWorld");
        system.actorOf(Props.create(Terminator.class, a), "terminator");

        ActorRef greeter = system.actorOf(Props.create(Greeter.class), "greeter");
        // tell it to perform the greeting
        greeter.tell(Greeter.Msg.GREET, a);
    }

}
