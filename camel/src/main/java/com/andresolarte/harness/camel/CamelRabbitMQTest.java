package com.andresolarte.harness.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;


public class CamelRabbitMQTest {

    public static Main main;

    public static void main(String... args) {
        main = new Main();

        main.bind("handler",new Handler());
        // add routes
        main.addRouteBuilder(new RabbitMQRouteBuilder());



        System.out.println("Starting Camel. Use ctrl + c to terminate the JVM.\n");
        try {
            main.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class RabbitMQRouteBuilder extends RouteBuilder {
        public void configure() throws Exception {
            from("timer:timer1?repeatCount=1&delay=1000").to("bean:handler");
            //from("rabbitmq://localhost/A?routingKey=B").to("bean:handler");
        }
    }

    public static class Handler {
        public void onMessage(Message message) {
            System.out.println("Message received");
        }
    }
}
