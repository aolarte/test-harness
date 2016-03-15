package com.andresolarte.harness.amq;

import org.apache.activemq.broker.BrokerService;

public class EmbeddedBroker {

    public static void main(String... args) {
        BrokerService broker = new BrokerService();

        try {
            broker.addConnector("tcp://localhost:61616");
            broker.setPersistent(false);
            broker.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
