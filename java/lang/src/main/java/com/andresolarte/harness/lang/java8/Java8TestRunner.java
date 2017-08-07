package com.andresolarte.harness.lang.java8;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;


public class Java8TestRunner {

    public static void main(String[] args) {
        new Java8TestRunner().runWalk();
    }


    Function<String, Runnable> classCreator = className -> {
        Runnable ret = null;
        try {
            Class clazz = Class.forName(className);
            ret = (Runnable) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    };

    public void runWalk() {
        try {
            String packageName = this.getClass().getPackage().getName();
            Path src = new File(Constants.SRC_LOCATION + packageName.replaceAll("\\.", "/")).toPath();
            Files.walk(src)
                    .filter(p -> p.toString().endsWith("Test.java"))
                    .sorted()
                    .map(p -> src.relativize(p))
                    .map(Path::toString)
                    .map(f -> f.replaceAll("/", ".").replaceAll(".java", ""))
                    .map(f -> packageName + "." + f)
                    .peek(c -> System.out.println("********* " + c + " *********"))
                    .map(classCreator)
                    .filter(p -> p != null)
                    .forEach(Runnable::run);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
