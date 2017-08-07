package com.andresolarte.harness.amq;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class AMQClientTest {
    private String url="tcp://localhost:61616";

    private String destinationName="test.queue";

    public static void main(String[] args) {
        try {
            if ("send".equalsIgnoreCase(args[0])) {
                new AMQClientTest().sendMessage();
            }

            if ("dlq".equalsIgnoreCase(args[0])) {
                new AMQClientTest().forceToDLQ();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void sendMessage() throws Exception {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);


        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session =connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        Queue destination=session.createQueue(destinationName);

        MessageProducer producer=session.createProducer(destination);
        TextMessage textMessage=session.createTextMessage("1234");
        producer.send(textMessage);
        producer.close();

        session.close();
        connection.close();
    }


    public void forceToDLQ() throws Exception {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

        boolean done=false;
        while (!done) {
            Connection connection = connectionFactory.createConnection();


            connection.start();

            Session session = connection.createSession(true, Session.SESSION_TRANSACTED);
            Queue destination = session.createQueue(destinationName);

            MessageConsumer consumer = session.createConsumer(destination);
            Message message = consumer.receive(500);
            if (message==null) {
                System.out.println("No message found");
                done=true;
            } else {
                System.out.println("Message found rolling back");
                session.rollback();
            }

            session.close();
            connection.close();
        }
    }
}
