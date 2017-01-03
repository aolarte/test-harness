package com.andresolarte.harness.lang.java8.topic2_2;

import com.andresolarte.harness.lang.java8.domain.Person;

import java.util.List;
import java.util.function.Function;

public class FunctionTest implements Runnable {

    public void run() {
        List<Person> list1 = Person.createShortList();

        System.out.println("===Anonymous Class===");
        for (Person person : list1) {
            System.out.println(
                    person.printCustom(new Function<Person, String>() {
                        @Override
                        public String apply(Person person) {
                            return "Name: " + person.getFirstName() + " EMail: " + person.getEmail();
                        }
                    })
            );
        }


        // Statement Lambda
        Function<Person, String> statementLambda = p -> {
            return "Name: " + p.getFirstName() + " EMail: " + p.getEmail();
        };

        System.out.println("\n===Statement Lambda===");
        for (Person person : list1) {
            System.out.println(
                    person.printCustom(statementLambda)
            );
        }

        // Expression Lambda

        Function<Person, String> expressionLambda = p -> "Name: " + p.getFirstName() + " EMail: " + p.getEmail();

        System.out.println("\n===Expression Lambda===");
        for (Person person : list1) {
            System.out.println(
                    person.printCustom(expressionLambda)
            );
        }

        // In line Expression Lambda
        System.out.println("\n===In line Expression Lambda===");
        for (Person person : list1) {
            System.out.println(
                    person.printCustom(p -> "Name: " + p.getFirstName() + " EMail: " + p.getEmail())
            );
        }


        Function<Person, String> andThenComposedFunction = expressionLambda.andThen(x -> x + " new");

        //andThen Composed Lambda
        System.out.println("\n===andThen Composed Lambda===");
        for (Person person : list1) {
            System.out.println(
                    person.printCustom(andThenComposedFunction)
            );
        }

        Function<Person, String> composedFunction = expressionLambda.compose(x -> {
            x.setEmail(x.getEmail().toUpperCase());
            return x;
        });

        // Composed Lambda
        System.out.println("\n===Composed Lambda===");
        for (Person person : list1) {
            System.out.println(
                    person.printCustom(composedFunction)
            );
        }

    }
}