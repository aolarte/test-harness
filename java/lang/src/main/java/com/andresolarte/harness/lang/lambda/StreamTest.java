package com.andresolarte.harness.lang.lambda;

import com.andresolarte.harness.lang.java8.domain.Person;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String... args) {
        List<Person> personList = Person.createShortList();
        List<Person> personList1 = Arrays.asList(personList.toArray(new Person[personList.size()]));

        List<Person> personList2 = Stream.concat(personList.stream(), personList1.stream())
                .collect(Collectors.toList());
        personList2.add(new Person());
        personList2.add(new Person());

    }

}
