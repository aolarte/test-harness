package com.andresolarte.harness.lang.java8.topic3_2;

import java.util.Arrays;
import java.util.List;


public class StreamsTest implements Runnable {

    public void run() {
        System.out.println("*********StreamsTest*********");
        List<String> myList =
                Arrays.asList("a1", "a2", "b1", "c2", "c1");

        myList
                .stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);

    }
}