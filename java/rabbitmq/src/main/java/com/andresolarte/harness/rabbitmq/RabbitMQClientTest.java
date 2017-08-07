package com.andresolarte.harness.rabbitmq;


import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeoutException;

public class RabbitMQClientTest {
    public static final String QUEUE_NAME = "testQueue";
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    public static final BuiltinExchangeType EXCHANGE_TYPE = BuiltinExchangeType.TOPIC;


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
        createConsumer(factory, "group1");
        createConsumer(factory, "group2");
        sendMessage(factory);
        Thread.sleep(100);
        sendMessage(factory);
    }

    private void sendMessage(ConnectionFactory factory) throws IOException, TimeoutException {
        Connection conn = factory.newConnection();
        System.out.println("Connected");
        Channel channel = conn.createChannel();
        channel.exchangeDeclare(QUEUE_NAME, EXCHANGE_TYPE);
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Message at " + DATE_FORMAT.format(new Date()) ;
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println("Sent '" + message + "'");
        channel.close();
        conn.close();
    }

    private void createConsumer(ConnectionFactory factory, final String routingKey) throws IOException, TimeoutException {
        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();
        channel.exchangeDeclare(QUEUE_NAME, BuiltinExchangeType.TOPIC);
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, QUEUE_NAME, routingKey);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Received '" + message + "' @" + routingKey);
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
