package com.andresolarte.harness.lang.java8.topic1_1;


public class InnerClassTest implements Runnable {
    int x = 10;

    public void run() {
        System.out.println("===Inner Class Test===");
        ParentClass parent = new ParentClass();
        parent.printX();
        parent.printY();

        ParentClass.NestedClass nestedClass = parent.new NestedClass();
        nestedClass.printX();
        nestedClass.printY();

        ParentClass.InnerStaticClass innerStaticClass = new ParentClass.InnerStaticClass();
        innerStaticClass.printX();
        innerStaticClass.printY();

        class LocalClass {
            public void printX() {
                System.out.println("LocalClass X: " + x);
            }
        }

        LocalClass localClass = new LocalClass();
        localClass.printX();

        Runnable anonymousClass = new Runnable() {
            @Override
            public void run() {
                System.out.println("anonymousClass X: " + x);
            }
        };
        anonymousClass.run();
    }
}
