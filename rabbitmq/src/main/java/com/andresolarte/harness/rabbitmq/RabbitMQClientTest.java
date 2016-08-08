package com.andresolarte.harness.rabbitmq;


import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQClientTest {

    public static void main(String... args) {
        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void test() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        //factory.setVirtualHost(virtualHost);
        factory.setHost("localhost");
        factory.setPort(5672);
        Connection conn = factory.newConnection();
        System.out.println("Connected");
    }
}
