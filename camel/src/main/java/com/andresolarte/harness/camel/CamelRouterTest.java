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

public class CamelRouterTest implements Serializable {
    public static void main(String[] args) {
        new CamelRouterTest().run();
    }

    private Main main;

    private ControlBean controlBean = new ControlBean();

    public void run() {
        main = new Main();

        // bind beans into the registry
        main.bind("bean1", new MyBean1());
        main.bind("bean2", new MyBean2());
        main.bind("controlBean", controlBean);

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
                    .when(method("controlBean","isToggle"))
                        .to("direct:route1")
                    .otherwise()
                        .to("direct:route2");

            from("direct:route1")
                    .to("bean:bean1?method=submit"); // Method prevents "Ambiguous method invocations possible" errors

            from("direct:route2")
                    .to("bean:bean2");


        }
    }

    public interface Service {
        Result submit(String s);
    }


    public static class MyBean1 implements Service{
        public Result submit(String s) {
            return new Result("Success: 1");
        }

        public String format(String s) {
            return s;
        }

    }

    public static class MyBean2  {
        public Result submit(String s) {
            return new Result("OK: 2");
        }
    }

    public static class ControlBean {
        private boolean toggle = true;

        public boolean isToggle() {
            return toggle;
        }

        public void setToggle(boolean toggle) {
            this.toggle = toggle;
        }
    }


    private class Events extends MainListenerSupport {

        public void afterStart(MainSupport main) {
            System.out.println("After Start Event fired.");
            CamelContext context=main.getCamelContexts().get(0);
            try {
                Service service = new ProxyBuilder(context).endpoint("direct:start").build(Service.class);
                for (int i=0;i<20;i++) {
                    Result ret=service.submit("#"+i);
                    System.out.println("Got: " + ret.data + " for " + i);
                }
                System.out.println("Toggle !!!!");
                controlBean.setToggle(false);

                Thread.sleep(500);
                for (int i=0;i<20;i++) {
                    Result ret=service.submit("#"+i);
                    System.out.println("Got: " + ret.data + " for " + i);
                }
                Thread.sleep(500);
                context.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
