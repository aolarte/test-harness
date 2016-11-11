package com.andresolarte.harness.lang.reflect;

import java.lang.reflect.Proxy;

public class DynamicProxyTest implements Runnable{

    public static void main(String... args) {

        DynamicProxyTest object = new DynamicProxyTest();
        Runnable proxy = (Runnable)Proxy.newProxyInstance(
                DynamicProxyTest.class.getClassLoader(),
                new Class[]{Runnable.class},
                new TestInvocationHandler(object));
        proxy.run();

        System.out.println("proxy.getClass(): " + proxy.getClass());
        System.out.println("proxy instanceof Runnable: " + (proxy instanceof Runnable));
        System.out.println("Runnable.class.isAssignableFrom(proxy.getClass()): " + (Runnable.class.isAssignableFrom(proxy.getClass())));
    }

    public void run() {
        System.out.println("Test");
    }
}
