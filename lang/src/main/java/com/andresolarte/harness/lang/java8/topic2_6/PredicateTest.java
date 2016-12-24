package com.andresolarte.harness.lang.java8.topic2_6;

import com.andresolarte.harness.lang.java8.domain.Gender;
import com.andresolarte.harness.lang.java8.domain.Person;

import java.util.function.Predicate;
import java.util.function.UnaryOperator;


public class PredicateTest implements Runnable {

    public void run() {

        UnaryOperator<String> supplierLambda = p -> p.toUpperCase();

        System.out.println(supplierLambda.apply("Test"));

        Person person = new Person.Builder()
                .givenName("Betty")
                .surName("Jones")
                .age(15)
                .gender(Gender.FEMALE)
                .email("betty.jones@example.com")
                .phoneNumber("211-33-1234")
                .address("22 4th St, New Park, CO 222333")
                .build();

        Predicate<Person> isFemale = p -> p.getGender() == Gender.FEMALE;
        Predicate<Person> isAdult = p -> p.getAge() >= 18;

        System.out.println("Is Female: " + isFemale.test(person));


        System.out.println("Is Adult: " + isAdult.test(person));
        System.out.println("Is NOT Adult: " + isAdult.negate().test(person));
        System.out.println("Is Female Adult: " + isFemale.and(isAdult).test(person));
        System.out.println("Is Female and NOT Adult: " + isFemale.and(isAdult.negate()).test(person));

    }
}