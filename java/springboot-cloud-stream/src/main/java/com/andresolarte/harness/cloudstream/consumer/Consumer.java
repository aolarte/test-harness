package com.andresolarte.harness.cloudstream.consumer;

import com.andresolarte.harness.cloudstream.Person;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class Consumer {

    @StreamListener(Sink.INPUT)
    public void handle(Person person) {
        System.out.println("Got person: " + person);
    }
}
