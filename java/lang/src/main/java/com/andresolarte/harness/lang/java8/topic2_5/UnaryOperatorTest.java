package com.andresolarte.harness.lang.java8.topic2_5;

import java.util.function.UnaryOperator;


public class UnaryOperatorTest implements Runnable {

    public void run() {

        UnaryOperator<String> supplierLambda = p -> p.toUpperCase();

        System.out.println(supplierLambda.apply("Test"));


    }
}