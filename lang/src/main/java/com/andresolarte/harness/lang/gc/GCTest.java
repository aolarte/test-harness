package com.andresolarte.harness.lang.gc;

import java.io.PrintStream;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

public class GCTest {
    private static String TOTAL = "Total: ";
    private static String FREE = " Free: ";
    private static String LN = "\n";
    private static long MEG=(1024*1024);
    private static long limit=Double.valueOf(1.5 * MEG).longValue()  ;

    private static PrintStream out=System.out;
    private static Runtime runtime=Runtime.getRuntime();

    public static void main(String... args) {
        new GCTest().run();
    }

    private List<TestObject> list=new ArrayList<>();




    public void run() {
        System.out.println("PID: " + ManagementFactory.getRuntimeMXBean().getName());
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long free;
        long total;
        int i;
        while(true) {

            for (i=0;i<2000;i++) {
                free = runtime.freeMemory();
                if (free > limit) {
                    list.add(new TestObject(i));
                }
            }
            collectGarbage();
            free = runtime.freeMemory()/MEG;
            total = runtime.totalMemory()/MEG;
            out.print(TOTAL);
            out.print(total);
            out.print(FREE);
            out.print(free);
            out.print(LN);

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void collectGarbage() {

        System.gc();
        System.runFinalization();

    }

    public static class TestObject {
        public TestObject(long i) {
            this.i = i;
        }

        private final long i;
    }
}
