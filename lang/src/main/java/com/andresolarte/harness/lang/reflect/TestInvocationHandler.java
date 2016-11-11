package com.andresolarte.harness.lang.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TestInvokationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.nanoTime();
        try {
            return method.invoke(proxy, args);
        } finally {
            long endTime = System.nanoTime();
            System.out.println("Execution time: " + (endTime - startTime));
        }
    }
}
