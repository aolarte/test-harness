package com.andresolarte.harness.akka.actor;

import akka.actor.*;
import com.andresolarte.harness.akka.pojo.IDuplicator;


public class DuplicateTest {

    public static void main(String[] args) {
        System.out.println("Creating System");

        ActorSystem system = ActorSystem.create("Hello");
        TypedActorExtension typed = TypedActor.get(system);
        IDuplicator duplicator =
                typed.typedActorOf(new TypedProps<Duplicator>(IDuplicator.class,Duplicator.class));

        duplicator.duplicate(2,1000);

    }
}
