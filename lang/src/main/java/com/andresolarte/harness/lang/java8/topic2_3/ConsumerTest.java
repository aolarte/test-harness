package com.andresolarte.harness.lang.java8.topic2_3;

import com.andresolarte.harness.lang.java8.domain.Person;

import java.util.List;
import java.util.function.Consumer;

public class ConsumerTest implements Runnable {

    public void run() {

        List<Person> list1 = Person.createShortList();

        Consumer<Person> expressionLambda = p -> System.out.println(p.getFirstName());


        System.out.println("===Expression Lambda===");
        for (Person person : list1) {
            person.consume(expressionLambda);
        }

        System.out.println("===andThen Composed Lambda===");
        for (Person person : list1) {
            person.consume(expressionLambda.andThen(p -> System.out.println(p.getLastName())));
        }


    }
}