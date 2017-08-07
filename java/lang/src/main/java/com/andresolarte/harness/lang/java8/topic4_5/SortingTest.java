package com.andresolarte.harness.lang.java8.topic4_5;

import com.andresolarte.harness.lang.java8.domain.Person;

import java.util.Comparator;
import java.util.List;

public class SortingTest implements Runnable {
    @Override
    public void run() {
        System.out.println("4.5   *********SortingTest*********");
        List<Person> persons = Person.createLongList();
        System.out.println("===sorted===");
        persons.stream().sorted(Comparator.comparing(Person::getAge)).forEach(p -> System.out.println("Person: " + p.formatName() + " " + p.getAge()));
        System.out.println("===thenComparing===");
        persons.stream().sorted(Comparator.comparing(Person::getAge).thenComparing(Comparator.comparing(Person::formatName).reversed())).forEach(p -> System.out.println("Person: " + p.formatName() + " " + p.getAge()));
    }
}
