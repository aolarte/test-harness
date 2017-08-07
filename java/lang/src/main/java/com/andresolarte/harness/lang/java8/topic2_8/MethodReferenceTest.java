package com.andresolarte.harness.lang.java8.topic2_8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class MethodReferenceTest implements Runnable {

    public void run() {

        List<String> names = new ArrayList<>();
        names.add("George");
        names.add("Fred");
        names.add("Ron");
        System.out.println("===Reference to a static method===");

        names.forEach(name -> System.out.println(name));


        names.forEach(System.out::println);


        UnaryOperator<String> toUpperCase = String::toUpperCase;
        System.out.println(toUpperCase.apply("Hello"));

        BiFunction<String, String, Boolean> f1 = String::equalsIgnoreCase;
        System.out.println(f1.apply("Hello", "HELLO"));

        System.out.println("===Reference to a constructor===");

        Function<char[], String> toString = String::new;
        System.out.println(toString.apply(new char[]{'H', 'i'}));


        System.out.println("===Reference to an instance method of a particular object===");
        Integer i = new Integer(1);

        Supplier<String> supplier = i::toString;
        System.out.println(supplier.get());
    }
}
