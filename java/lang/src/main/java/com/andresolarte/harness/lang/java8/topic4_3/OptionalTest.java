package com.andresolarte.harness.lang.java8.topic4_3;

import com.andresolarte.harness.lang.java8.domain.Person;

import java.util.List;
import java.util.Optional;


public class OptionalTest implements Runnable {

    public void run() {

        System.out.println("4.3   *********OptionalTest*********");
        System.out.println("===get===");
        List<Optional<Person>> list1 = Person.createOptionalList(Person.createShortList());
        list1.stream().forEach(o -> System.out.println("Person: " + o.get().formatName()));
        //Add some null objects
        list1.add(Optional.ofNullable(null));
        list1.add(Optional.empty());

        System.out.println("===orElseGet===");
        list1.stream().forEach(o -> System.out.println("Person: " + o.orElseGet(Person::createDefaultPerson).formatName()));

        System.out.println("===orElse===");
        list1.stream().forEach(o -> System.out.println("Person: " + o.orElse(Person.createDefaultPerson()).formatName()));


        System.out.println("===isPresent===");
        list1.stream().filter(Optional::isPresent).forEach(o -> System.out.println("Person: " + o.get().formatName()));

        System.out.println("===ifPresent===");
        list1.stream().forEach(o -> o.ifPresent(p -> System.out.println("Person: " + p.formatName())));
    }

}