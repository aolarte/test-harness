package com.andresolarte.harness.lang.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TestInvocationHandler implements InvocationHandler {
    private final Object target;

    public TestInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.nanoTime();
        System.out.println("Starting profiling");
        try {
            return method.invoke(target, args);
        } finally {
            long endTime = System.nanoTime();
            System.out.println("Execution time: " + (endTime - startTime));
        }
    }
}
