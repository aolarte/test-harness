package com.andresolarte.harness.camel;

import com.andresolarte.harness.camel.pojos.OrderStatusUpdateAction;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.main.Main;

import java.io.Serializable;

import static org.apache.activemq.camel.component.ActiveMQComponent.activeMQComponent;

/**
 * Created by aolarte on 1/6/16.
 */
public class KafkaCamelTest implements Serializable {
    public static void main(String[] args) {
        new KafkaCamelTest().run();
    }

    private Main main;

    public void run() {

        main = new Main();

        // enable hangup support so you can press ctrl + c to terminate the JVM
        main.enableHangupSupport();
        // bind MyBean into the registry
        main.bind("foo", new MyBean());
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
            from("scheduler:name?initialDelay=1000&delay=10000000000").process(new Processor() {

                public void process(Exchange exchange) throws Exception {
                    System.out.println("Sending message");
                    exchange.getIn().setBody("Test Message from Camel Kafka Component Final",String.class);
                    exchange.getIn().setHeader(KafkaConstants.PARTITION_KEY, 0);
                    exchange.getIn().setHeader(KafkaConstants.KEY, "1");
                }
            }).to("kafka:guest:9092?topic=test");

            from("kafka:guest:9092?topic=test&groupId=testing&autoOffsetReset=earliest&consumersCount=1").to("bean:foo");
        }
    }

    public static class MyBean {
        public void onMessage(final Exchange exchange) {
            String messageKey = "";
          if (exchange.getIn() != null) {
              Message message = exchange.getIn();
              Integer partitionId = (Integer) message.getHeader(KafkaConstants.PARTITION);
              String topicName = (String) message.getHeader(KafkaConstants.TOPIC);
              if (message.getHeader(KafkaConstants.KEY) != null)
               messageKey = (String) message.getHeader(KafkaConstants.KEY);
               Object data = message.getBody();
                System.out.println("topicName :: " + topicName + " partitionId :: "
                + partitionId + " messageKey :: "
                + messageKey + " message :: "
               + data + "\n");}
        }
    }


}
