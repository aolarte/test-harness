package com.andresolarte.harness.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

public class FlakyCommand extends HystrixCommand<String> {
    public static HystrixCommandGroupKey GROUP_KEY=HystrixCommandGroupKey.Factory.asKey("ExampleGroup");
    public static HystrixCommandKey COMMAND_KEY=HystrixCommandKey.Factory.asKey("FlakyCommand");

    private static int COUNTER =0;

    private final String name;

    public FlakyCommand(String name) {
        super(Setter.withGroupKey(GROUP_KEY).andCommandKey(COMMAND_KEY));
        this.name = name;
    }

    @Override
    protected String run() {
        COUNTER = COUNTER +1;
        if (COUNTER>2) {
            COUNTER = 0;
            throw new RuntimeException("Over capacity");
        }
        return name.toUpperCase();
    }

    @Override
    protected String getFallback() {
        return name + " (over capacity)";
    }
}