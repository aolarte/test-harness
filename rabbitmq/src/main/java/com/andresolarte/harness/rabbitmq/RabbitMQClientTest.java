package com.andresolarte.harness.rabbitmq;


import com.rabbitmq.client.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeoutException;

public class RabbitMQClientTest {
    public static final String QUEUE_NAME = "testQueue";
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    public static void main(String... args) {
        try {
            new RabbitMQClientTest().test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void test() throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        //factory.setVirtualHost(virtualHost);
        factory.setHost("localhost");
        factory.setPort(5672);
        createConsumer(factory);
        sendMessage(factory);
        Thread.sleep(100);
        sendMessage(factory);
    }

    private void sendMessage(ConnectionFactory factory) throws IOException, TimeoutException {
        Connection conn = factory.newConnection();
        System.out.println("Connected");
        Channel channel = conn.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Message at " + DATE_FORMAT.format(new Date()) ;
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println("Sent '" + message + "'");
    }

    private void createConsumer(ConnectionFactory factory) throws IOException, TimeoutException {
        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Received '" + message + "'");
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
