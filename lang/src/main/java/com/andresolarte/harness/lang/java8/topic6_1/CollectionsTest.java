package com.andresolarte.harness.lang.java8.topic6_1;

import com.andresolarte.harness.lang.java8.domain.Person;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class CollectionsTest implements Runnable {
    public void run() {
        List<Person> persons = Person.createLongList();
        System.out.println("Original length: " + persons.size());
        persons.removeIf(p -> p.getAge() < 50);
        System.out.println("Older than 50: " + persons.size());
        persons.replaceAll(p -> {
            p.setFirstName("John");
            p.setLastName("Doe");
            return p;
        });
        persons.forEach(p -> System.out.println("Name: " + p.formatName() + " " + p.getAge()));

        Map<String, Person> map = Person.createLongList().stream().collect(Collectors.toMap(Person::formatName, x -> x));
        map.forEach((n, p) -> System.out.println("Name " + n + " age: " + p.getAge()));
        System.out.println("Locate record:" + map.computeIfAbsent("Test Person", k -> Person.createFakePerson()).formatName());

        System.out.println("Locate record:" + map.computeIfAbsent("Test Person", k -> Person.createFakePerson()).formatName());

        System.out.println("Locate record:" + map.computeIfAbsent("Michael Michaels", k -> Person.createFakePerson()).formatName());

    }
}
