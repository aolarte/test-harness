package com.andresolarte.harness.lang.threads;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.stream.IntStream;

public class ForkJoinPoolTest {

    final static ForkJoinPool.ForkJoinWorkerThreadFactory FACTORY = pool -> {
        final ForkJoinWorkerThread worker = ForkJoinPool.defaultForkJoinWorkerThreadFactory.newThread(pool);
        worker.setName("my-thread-prefix-name-" + worker.getPoolIndex());
        return worker;
    };


    public static void main(String... args) throws InterruptedException {

        IntStream.range(0, 10).parallel().forEach((number) -> {
            try {
                System.out.println("ThreadName: " + Thread.currentThread().getName());
                Thread.sleep(5);
            } catch (InterruptedException e) {
            }
        });
        System.out.println("Done #1");

        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors(), FACTORY, null, false);
        forkJoinPool.submit(() -> IntStream.range(0, 10).parallel().forEach((number) -> {
            try {
                System.out.println("ThreadName: " + Thread.currentThread().getName());
                Thread.sleep(5);
            } catch (InterruptedException e) {
            }
        }));

        Thread.sleep(20_000);

    }
}
