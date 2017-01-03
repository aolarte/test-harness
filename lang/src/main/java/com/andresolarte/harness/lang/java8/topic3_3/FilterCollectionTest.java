package com.andresolarte.harness.lang.java8.topic3_3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterCollectionTest implements Runnable {
    @Override
    public void run() {

        System.out.println("3.3   *********FilterCollectionTest*********");
        List<String> list = Arrays.asList("one", "two", "three", "four");

        List<String> longerStrings = list.stream().filter(i -> i.length() > 3).collect(Collectors.toList());
        longerStrings.forEach(i -> System.out.println("Item: " + i));
    }
}
