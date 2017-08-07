package com.andresolarte.harness.lang.java8.topic4_6;

import com.andresolarte.harness.lang.java8.domain.Gender;
import com.andresolarte.harness.lang.java8.domain.Person;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class CollectorTest implements Runnable {
    @Override
    public void run() {
        System.out.println("4.6   *********CollectorTest*********");
        List<Person> personList = Person.createShortList();
        System.out.println("===averaging===");
        System.out.println("Average age: " + personList.stream().collect(Collectors.averagingInt(Person::getAge)));
        System.out.println("===partitionging===");
        Map<Boolean, List<Person>> personsByGender = personList.stream().collect(Collectors.partitioningBy(p -> p.getGender() == Gender.FEMALE));
        personsByGender.get(false).forEach(p -> System.out.println("MALE: " + p.formatName()));
        personsByGender.get(true).forEach(p -> System.out.println("FEMALE: " + p.formatName()));
        System.out.println("===joining===");
        String nameList = personList.stream().map(Person::formatName).collect(Collectors.joining(", "));
        System.out.println("Name List: " + nameList);
        System.out.println("===groupingBy===");
        Map<Integer, List<Person>> personsByAge = personList.stream().collect(Collectors.groupingBy(Person::getAge));
        personsByAge.keySet().stream().sorted().forEach(k -> System.out.println("Age: " + k + " person count " + personsByAge.get(k).size()));
        System.out.println("===groupingBy with downstream collector===");
        Map<Gender, Double> averageAgeByGender = personList.stream().collect(Collectors.groupingBy(Person::getGender, Collectors.averagingInt(Person::getAge)));
        averageAgeByGender.forEach((g, v) -> System.out.println(g + " average age: " + v));
    }
}
