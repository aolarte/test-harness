package com.andresolarte.harness.lang.java8.topic3_1;

import java.util.ArrayList;
import java.util.List;

public class ForEachTest implements Runnable {

    public void run() {
        System.out.println("*********ForEachTest*********");

        List<String> names = new ArrayList<>();
        names.add("George");
        names.add("Fred");
        names.add("Ron");

        names.forEach(name -> System.out.println(name));

        names.forEach(System.out::println);
    }
}
