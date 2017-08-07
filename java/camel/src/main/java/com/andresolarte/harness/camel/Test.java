package com.andresolarte.harness.camel;

public class Test {

    public static void main(String... args ) {
        while(true) {
            System.out.println("Running ");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
