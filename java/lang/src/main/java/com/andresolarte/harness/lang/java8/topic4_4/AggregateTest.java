package com.andresolarte.harness.lang.java8.topic4_4;

import com.andresolarte.harness.lang.java8.domain.Person;

import java.util.Comparator;
import java.util.List;


public class AggregateTest implements Runnable {
    @Override
    public void run() {
        System.out.println("4.4   *********AggregateTest*********");

        List<Person> personList = Person.createLongList();
        personList.stream().min(Comparator.comparing(Person::getAge)).ifPresent(p -> System.out.println("Youngest person: " + p.formatName() + " " + p.getAge()));
        personList.stream().max(Comparator.comparing(Person::getAge)).ifPresent(p -> System.out.println("Oldest person: " + p.formatName() + " " + p.getAge()));
        System.out.println("Persons older than 30: " + personList.stream().filter(p -> p.getAge() > 30).count());
        System.out.println("Average age: " + personList.stream().mapToInt(Person::getAge).average());

    }
}
