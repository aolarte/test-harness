package com.andresolarte.harness.camel;

import com.andresolarte.harness.camel.pojos.OrderStatusUpdateAction;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;

import static org.apache.activemq.camel.component.ActiveMQComponent.activeMQComponent;

public class SchedulerTest {



    public static void main(String[] args) {
        new SchedulerTest().run();
    }

    private Main main;

    public void run() {

        main = new Main();

        // bind MyBean into the registry

        // add routes
        main.addRouteBuilder(new SchedulerTest.MyRouteBuilder());



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

            from("scheduler://foo?delay=5000")
                    .process(exchange -> {
                        exchange.getIn().setHeader("key","value2");
                    })
                    .choice()
                        .when(simple("${in.header.key} == \"value1\""))
                        .to("direct:direct1")
                    .otherwise()
                        .to("direct:direct2");

            from("direct:direct1")
                    .log("Direct 1").stop();

            from("direct:direct2")
                    .log("Direct 2").stop();
        }
    }


}
