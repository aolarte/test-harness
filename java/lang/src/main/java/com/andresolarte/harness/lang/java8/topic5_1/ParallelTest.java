package com.andresolarte.harness.lang.java8.topic5_1;

import com.andresolarte.harness.lang.java8.domain.Person;


public class ParallelTest implements Runnable {
    @Override
    public void run() {
        Person.createLongList().stream().parallel().forEach(p -> System.out.println(p.formatName()));
    }
}
