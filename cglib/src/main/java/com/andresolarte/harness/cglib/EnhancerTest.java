package com.andresolarte.harness.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.MethodInterceptor;

import java.util.function.Supplier;


public class EnhancerTest implements Supplier<String> {

    public static void main(String... args) {
        printInfo(testFixedValue(),"Fixed");
        printInfo(testInvocationHandler(),"Handler");
        printInfo(testInvocationInterceptor(),"Interceptor");
    }

    public static Supplier<String> testFixedValue() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(EnhancerTest.class);
        enhancer.setCallback((FixedValue) () -> "Not Yet Implemented");
        return (Supplier) enhancer.create();
    }

    public static Supplier<String> testInvocationHandler() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(EnhancerTest.class);
        enhancer.setCallback((InvocationHandler) (proxy, method, args) -> {
            if(method.getDeclaringClass() != Object.class
                    && method.getReturnType() == String.class) {
                return "Test implementation!";
            } else {
                throw new RuntimeException("Do not know what to do.");
            }
        });
        return (Supplier) enhancer.create();
    }

    public static Supplier<String> testInvocationInterceptor() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(EnhancerTest.class);
        enhancer.setCallback((MethodInterceptor) (obj,  method,  args, proxy) -> {
            if("get".equalsIgnoreCase(method.getName())
                    && method.getReturnType() == String.class) {
                return "<p>" + proxy.invokeSuper(obj, args) + "</p>";
            } else {
                return proxy.invokeSuper(obj, args);
            }
        });
        return (Supplier) enhancer.create();

    }

    public static void printInfo(Supplier<String> proxy, String label) {
        System.out.println("**** " + label + " ****");
        try {
            System.out.println(proxy.get());
        } catch (Exception e) {
            System.out.println("Got Exception during get(): " + e.getMessage());
        }

        try {
            System.out.println(proxy.toString());
        } catch (Exception e) {
            System.out.println("Got Exception during toString(): " + e.getMessage());
        }

    }


    @Override
    public String get() {
        return "Hello Test!";
    }
}
