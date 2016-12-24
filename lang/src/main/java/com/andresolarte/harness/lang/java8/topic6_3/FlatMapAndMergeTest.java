package com.andresolarte.harness.lang.java8.topic6_3;

import com.andresolarte.harness.lang.java8.domain.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FlatMapAndMergeTest implements Runnable {
    public void run() {
        System.out.println("6.3   *********FlatMapAndMergeTest*********");
        List<Person> persons = Person.createLongList();
        List<String> names = persons.stream().map(Person::formatName).flatMap(n -> Stream.of(n.split(" +"))).distinct().sorted().collect(Collectors.toList());
        System.out.println("Unique names: " + names);
        List<String> nameList = persons.stream().map(Person::formatName).flatMap(n -> Stream.of(n.split(" +"))).collect(Collectors.toList());
        Map<String, Integer> nameCount = new HashMap<>();

        Consumer<String> consumer = s -> {
            nameCount.merge(s, 1, (v, n) -> v + 1);
        };
        nameList.forEach(consumer);
        nameCount.forEach((name, count) -> System.out.println(name + ": " + count));
    }
}
