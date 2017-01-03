package com.andresolarte.harness.lang.java8.topic1_1;


public class ParentClass {

    int x = 1;
    int y = 11;

    public static class InnerStaticClass {
        int x = 2;

        public void printX() {
            System.out.println("InnerStaticClass X: " + x);
        }

        public void printY() {
            System.out.println("No y variable is accesible to this class");
            //System.out.println("InnerStaticClass Y: " + y); WILL NOT COMPILE
        }

    }

    public class NestedClass {
        int x = 3;

        public void printX() {
            System.out.println("NestedClass X: " + x);
            System.out.println("NestedClass Parent X: " + ParentClass.this.x);
        }

        public void printY() {
            System.out.println("NestedClass Y: " + y);
        }
    }

    public void printX() {
        System.out.println("ParentClass X: " + x);
    }

    public void printY() {
        System.out.println("ParentClass Y: " + y);
    }
}
