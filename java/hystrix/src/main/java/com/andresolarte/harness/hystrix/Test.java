package com.andresolarte.harness.hystrix;

import com.netflix.hystrix.metric.HystrixCommandCompletionStream;
import com.netflix.hystrix.metric.HystrixCommandStartStream;
import rx.Observable;

import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class Test {

    public static void main (String... args) throws Exception {
        HystrixCommandStartStream.getInstance(FlakyCommand.COMMAND_KEY).observe()
                .subscribe(event -> System.out.println("Event start"));
        HystrixCommandCompletionStream.getInstance(FlakyCommand.COMMAND_KEY).observe()
                .subscribe(event -> System.out.println("Event completed"));


        String s = new FlakyCommand("Test").execute();
        System.out.println("S: "+ s);
        Future<String> stringFuture = new FlakyCommand("Test").queue();
        System.out.println("Future: "+ stringFuture.get());
        Observable<String> stringObservable = new FlakyCommand("Test").observe();
        stringObservable.subscribe(x-> System.out.println("Observable: " + x));

        IntStream.range(0,10).forEach(i->
            System.out.println("S: "+ new FlakyCommand("Test").execute()));
    }
}
