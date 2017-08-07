package com.andresolarte.harness.lang.java8.topic3_4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


public class LazyOperationsTest implements Runnable {
    public void run() {

        System.out.println("3.4   *********LazyOperationsTest*********");

        System.out.println("===Filtering with short circuit===");
        List<String> names = Arrays.asList("Volha", "Ivan", "Daria", "John");
        Stream<String> stream = names.stream()
                .filter(s -> {
                    System.out.println("filtering " + s);
                    return s.length() == 4;
                })
                .map(s -> {
                    System.out.println("uppercasing " + s);
                    return s.toUpperCase();
                });
        System.out.println("Stream was filtered and mapped...");
        String name = stream.findFirst().get();
        System.out.println(name);
        System.out.println("===Filtering all items===");
        stream = names.stream()
                .filter(s -> {
                    System.out.println("filtering " + s);
                    return s.length() == 4;
                })
                .map(s -> {
                    System.out.println("uppercasing " + s);
                    return s.toUpperCase();
                });
        stream.forEach(System.out::println);
    }
}
