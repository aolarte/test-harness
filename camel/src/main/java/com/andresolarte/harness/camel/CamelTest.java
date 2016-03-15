package com.andresolarte.harness.camel;

import com.andresolarte.harness.camel.pojos.OrderStatusUpdateAction;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;

import java.io.Serializable;

import static org.apache.activemq.camel.component.ActiveMQComponent.activeMQComponent;

/**
 * Created by aolarte on 1/6/16.
 */
public class CamelTest implements Serializable {
    public static void main(String[] args) {
        new CamelTest().run();
    }

    private Main main;

    public void run() {

        main = new Main();

        // enable hangup support so you can press ctrl + c to terminate the JVM
        main.enableHangupSupport();
        // bind MyBean into the registry
        main.bind("foo", new MyBean());
        main.bind("foo2", new MyBean2());
        // add routes
        main.addRouteBuilder(new MyRouteBuilder());



        System.out.println("Starting Camel. Use ctrl + c to terminate the JVM.\n");
        try {
            main.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class MyRouteBuilder extends RouteBuilder {
        @Override
        public void configure() throws Exception {
            this.getContext().addComponent("activemq", activeMQComponent("tcp://localhost:61616"));


            from("activemq:queue:XXX2?concurrentConsumers=10&mapJmsMessage=false&destination.consumer.prefetchSize=1")
                    .choice()
                    .when(simple("${body} is 'javax.jms.ObjectMessage' and ${body.object} is  'com.andresolarte.harness.camel.pojos.OrderStatusUpdateAction'"))
                    .to("bean:foo2")
                    .otherwise()
                    .to("bean:foo");

            from("timer:timer1?repeatCount=1&delay=1000").to("activemq:queue:XXX1");

            from("activemq:queue:XXX1?concurrentConsumers=5&mapJmsMessage=false&destination.consumer.prefetchSize=1")
                    .process(new Processor() {
                        public void process(Exchange exchange) {
                            Message in = exchange.getIn();
                            OrderStatusUpdateAction action=new OrderStatusUpdateAction();
                            action.getClass();
                            in.setBody(action);
                        }
                    })
                    .to("activemq:queue:XXX2");
        }
    }

    public static class MyBean {
        public void onMessage(final javax.jms.Message message) {
            System.out.println("My Bean");
        }
    }

    public static class MyBean2 {
        public void onMessage(final javax.jms.Message message) {
            System.out.println("My Bean 2!!!");
        }
    }
}
