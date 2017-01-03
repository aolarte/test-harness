package com.andresolarte.harness.lang.java8.topic4_2;

import com.andresolarte.harness.lang.java8.domain.Person;

import java.util.List;


public class SearchTest implements Runnable {
    public void run() {
        System.out.println("4.2   *********SearchTest*********");
        List<Person> personList = Person.createLongList();
        System.out.println("===findFirst===");
        Person person = personList.stream().sorted((a, b) -> a.getAge().compareTo(b.getAge())).findFirst().get();
        System.out.println(person.getFirstName() + " " + person.getLastName() + " " + person.getAge());

        System.out.println("===findAny===");
        person = personList.stream().sorted((a, b) -> a.getAge().compareTo(b.getAge())).findAny().get();
        System.out.println(person.getFirstName() + " " + person.getLastName() + " " + person.getAge());
        System.out.println("===Match===");
        System.out.println("anyMatch age>80 " + personList.stream().anyMatch(p -> p.getAge() > 80));
        System.out.println("anyMatch age>90 " + personList.stream().anyMatch(p -> p.getAge() > 90));
        System.out.println("allMatch age<80 " + personList.stream().allMatch(p -> p.getAge() < 80));
        System.out.println("allMatch age<90 " + personList.stream().allMatch(p -> p.getAge() < 90));
        System.out.println("noneMatch age>80 " + personList.stream().noneMatch(p -> p.getAge() > 80));
        System.out.println("noneMatch age>90 " + personList.stream().noneMatch(p -> p.getAge() > 90));
    }
}
