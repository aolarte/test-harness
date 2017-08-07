package com.andresolarte.harness.lang.java8.topic2_4;

import com.andresolarte.harness.lang.java8.domain.Gender;
import com.andresolarte.harness.lang.java8.domain.Person;

import java.util.function.Supplier;


public class SupplierTest implements Runnable {

    public void run() {

        Supplier<String> supplierLambda = () -> "Test string 1";

        System.out.println(supplierLambda.get());

        Person p = new Person.Builder()
                .givenName("Betty")
                .surName("Jones")
                .age(85)
                .gender(Gender.FEMALE)
                .email("betty.jones@example.com")
                .phoneNumber("211-33-1234")
                .address("22 4th St, New Park, CO 222333")
                .build();
        p.consumeEmail(() -> "betty@example.com");
        System.out.println("Email: " + p.getEmail());

        p.consumeEmail(() -> {
            return "betty.j@example.com";
        });
        System.out.println("Email: " + p.getEmail());


    }
}