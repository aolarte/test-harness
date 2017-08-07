package com.andresolarte.harness.camel;

import com.andresolarte.harness.camel.pojos.Event;
import com.andresolarte.harness.camel.pojos.Priority;
import com.andresolarte.harness.camel.pojos.Result;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.ProxyBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;
import org.apache.camel.main.MainListenerSupport;
import org.apache.camel.main.MainSupport;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SplitterAggregatorTest implements Serializable {

    public static void main(String[] args) {
        Main main = new Main();
        main.bind("urgentBean", new UrgentHandler());
        main.bind("normalBean", new NormalHandler());
        main.addRouteBuilder(new SplitterAggregatorRouteBuilder());
        main.addMainListener(new Events());

        try {
            main.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class RequestChunk {
        private final String priority;
        private final List<Event> list;

        public RequestChunk(String priority, List<Event> list) {
            this.priority = priority;
            this.list = list;
        }

        public List<Event> getList() {
            return list;
        }

        public String getPriority() {
            return priority;
        }
    }

    public interface Service {
        List<Result> submit(List<Event> list);
    }

    private static class SplitterAggregatorRouteBuilder extends RouteBuilder {
        @Override
        public void configure() throws Exception {
            from("direct:start")
                    .split().method(new PrioritySplitter())
                    .parallelProcessing()
                    .timeout(500)
                    .aggregationStrategy(new ListAggregator())
                    .choice()
                        .when(simple("${body.priority} == 'URGENT'"))
                            .to("bean:urgentBean")
                        .otherwise()
                            .to("bean:normalBean");
        }
    }

    private static class Events extends MainListenerSupport {
        public void afterStart(MainSupport main) {
            System.out.println("After Start Event fired. Running client test code");
            CamelContext context = main.getCamelContexts().get(0);
            try {
                Service service = new ProxyBuilder(context).endpoint("direct:start").build(Service.class);
                Event urgent1 = new Event("urgent1", Priority.URGENT);
                Event urgent2 = new Event("urgent2", Priority.URGENT);
                Event normal1 = new Event("normal1", Priority.NORMAL);
                List<Result> results = service.submit(Arrays.asList(urgent1, urgent2, normal1));
                results.forEach(r -> {
                    System.out.println("Got Result item: " + r.data + " " + r.reference);
                });
                Thread.sleep(500);
                context.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class ListAggregator implements AggregationStrategy {
        @Override
        public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
            if (oldExchange == null) {
                // the first time we only have the new exchange so it wins the first round
                return newExchange;
            }
            List<Result> oldResults = oldExchange.getIn().getBody(List.class);
            List<Result> newResults = newExchange.getIn().getBody(List.class);
            oldResults.addAll(newResults);
            return oldExchange;
        }
    }

    public static class PrioritySplitter {
        public List<RequestChunk> split(List<Event> events) {
            Map<Priority, List<Event>> eventsMap = events.stream().collect(Collectors.groupingBy(Event::getPriority));
            return eventsMap.keySet().stream()
                    .map(k -> new RequestChunk(k.toString(), eventsMap.get(k)))
                    .collect(Collectors.toList());
        }
    }

    public static class UrgentHandler {
        public List<Result> handle(RequestChunk requestChunk) {
            return requestChunk.getList().stream().map(e ->
                    new Result("URGENT HANDLER! Current thread: " + Thread.currentThread().getId(), e.getData())
            ).collect(Collectors.toList());
        }
    }

    public static class NormalHandler {
        public List<Result> handle(RequestChunk requestChunk) throws InterruptedException {
//            Thread.sleep(1000);
            return requestChunk.getList().stream().map(e ->
                    new Result("Current thread: " + Thread.currentThread().getId(), e.getData())
            ).collect(Collectors.toList());
        }
    }
}
