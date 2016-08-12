package com.andresolarte.harness.camel;

import com.andresolarte.harness.camel.pojos.Event;
import com.andresolarte.harness.camel.pojos.Priority;
import com.andresolarte.harness.camel.pojos.Result;
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
        main.bind("bean3", new MyBean3Urgent());
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
                    .choice()
                    .when(simple("${body?.priority} == ${type:com.andresolarte.harness.camel.pojos.Priority.URGENT}"))
                        .to("direct:route3")
                    .otherwise()
                        .loadBalance(weightedLoadBalancer)
                            .to("direct:route1")
                            .to("direct:route2");



            from("direct:route1")
                    .log("Submitting via route 1")
                    .to("bean:bean1");

            from("direct:route2")
                    .log("Submitting via route 2")
                    .to("bean:bean2");

            from("direct:route3")
                    .log("Submitting via route 3")
                    .to("bean:bean3");
        }
    }

    public interface Service {
        Result submit(Event e);
    }


    public static class MyBean1 {
        public Result onMessage(Event event) {
            System.out.println("My Bean 1: event: " + event.getData());
            return new Result("Success");
        }
    }

    public static class MyBean2 {
        public Result onMessage(Event event) {
            System.out.println("My Bean 2: event: " + event.getData());
            return new Result("OK");
        }
    }

    public static class MyBean3Urgent {
        public Result onMessage(Event event) {
            System.out.println("My Bean 3: event: " + event.getData());
            return new Result("Success (Urgent!)");
        }
    }

    private class Events extends MainListenerSupport {

        public void afterStart(MainSupport main) {
            System.out.println("After Start Event fired.");
            CamelContext context=main.getCamelContexts().get(0);
            try {
                Service service = new ProxyBuilder(context).endpoint("direct:start").build(Service.class);
                for (int i=0;i<20;i++) {
                    Event e=null;
                    if (i%5==0) {
                        e=new Event("#"+i, Priority.URGENT);
                    } else {
                        e=new Event("#"+i, Priority.NORMAL);
                    }

                    Result ret=service.submit(e);
                    System.out.println("Got: " + ret.data + " for " + e.getData());
                }

                Thread.sleep(500);
                context.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
