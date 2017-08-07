package com.andresolarte.harness.lang.java8.topic6_4;

import java.util.Arrays;
import java.util.stream.IntStream;


public class StreamSourcesTest implements Runnable {
    public void run() {
        Arrays.stream(new String[]{"one", "two", "three"}).map(String::toUpperCase).forEach(System.out::println);
        IntStream.range(1, 5).forEach(System.out::println);
        IntStream.rangeClosed(1, 5).forEach(System.out::println);

    }
}
