package com.andresolarte.harness.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.ProxyBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;
import org.apache.camel.main.MainListenerSupport;
import org.apache.camel.main.MainSupport;
import org.apache.camel.processor.loadbalancer.WeightedRoundRobinLoadBalancer;

import java.io.Serializable;
import java.util.Arrays;

public class CamelLoadBalanceTest implements Serializable {
    public static void main(String[] args) {
        new CamelLoadBalanceTest().run();
    }

    private Main main;

    public void run() {
        main = new Main();

        // bind beans into the registry
        main.bind("bean1", new MyBean1());
        main.bind("bean2", new MyBean2());
        // add routes
        main.addRouteBuilder(new MyRouteBuilder());
        // event to run our test
        main.addMainListener(new Events());

        try {
            main.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class MyRouteBuilder extends RouteBuilder {
        @Override
        public void configure() throws Exception {

            WeightedRoundRobinLoadBalancer weightedLoadBalancer = new WeightedRoundRobinLoadBalancer(Arrays.asList(9,1));
            from("direct:start")
                    .loadBalance(weightedLoadBalancer)
                    .to("direct:route1")
                    .to("direct:route2");

            from("direct:route1")
                    .log("Submitting via route 1")
                    .to("bean:bean1");

            from("direct:route2")
                    .log("Submitting via route 2")
                    .to("bean:bean2");
        }
    }

    public interface Service {
        String submit(Event e);
    }

    public static class Event {
        public Event(String type) {
            this.type = type;
        }

        public String type;
    }

    public static class MyBean1 {
        public String onMessage(Event event) {
            System.out.println("My Bean 1: event: " + event.type );
            return "Success";
        }
    }

    public static class MyBean2 {
        public String onMessage(Event event) {
            System.out.println("My Bean 2: event: " + event.type);
            return "OK";
        }
    }

    private class Events extends MainListenerSupport {

        public void afterStart(MainSupport main) {
            System.out.println("After Start Event fired.");
            CamelContext context=main.getCamelContexts().get(0);
            try {
                Service service = new ProxyBuilder(context).endpoint("direct:start").build(Service.class);
                for (int i=0;i<20;i++) {
                    Event e=new Event("#"+i);
                    String ret=service.submit(e);
                    System.out.println("Got: " + ret + " for " + e.type);
                }

                Thread.sleep(500);
                context.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
