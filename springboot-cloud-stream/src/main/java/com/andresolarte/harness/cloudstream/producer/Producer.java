package com.andresolarte.harness.cloudstream.producer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.InboundChannelAdapter;

@EnableBinding(Source.class)
public class Producer {

    @InboundChannelAdapter(Source.OUTPUT)
    public String greet() {
        System.out.println("Running");
        return "hello world " + System.currentTimeMillis();
    }
}
