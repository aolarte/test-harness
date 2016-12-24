package com.andresolarte.harness.lang.java8.topic6_2;

import com.andresolarte.harness.lang.java8.Constants;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class FilesTest implements Runnable {
    public void run() {
        try {
            System.out.println("===Files.find===");
            Path root = new File("/").toPath();

            Files.find(root, 1, (p, a) -> a.isDirectory()).forEach(p -> System.out.println("Directory found: " + p));
            System.out.println("===Files.lines===");
            Files.lines(new File(Constants.SRC_LOCATION + getClass().getPackage().getName().replaceAll("\\.", "/") +"/FilesTest.java")
                    .toPath())
                    .forEach(System.out::println);
            System.out.println("===Files.walk===");
            Files.walk(new File(".").toPath()).filter(p -> p.toString().endsWith("Test.java")).sorted().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
