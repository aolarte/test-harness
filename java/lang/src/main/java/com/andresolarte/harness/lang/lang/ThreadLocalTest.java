package com.andresolarte.harness.lang.lang;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.IntStream;

public class ThreadLocalTest {

    private static final ThreadLocal<SimpleDateFormat> dateFormat = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            System.out.println("Creating for thread: " + Thread.currentThread().getId());
            return new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        }

        @Override
        public SimpleDateFormat get() {
            System.out.println("Getting for thread: " + Thread.currentThread().getId());
            return super.get();
        }
    };

    private static final ThreadLocal<SimpleDateFormat> dateFormatHolder =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a"));

    public static void main(String... args) {

        dateFormatHolder.get().format(new Date());

        IntStream.range(0, 20).forEach(i -> new Thread(() -> {
            SimpleDateFormat sdf = dateFormat.get();
            System.out.println("Result: " + sdf.format(new Date()) +
                    " thread: " + Thread.currentThread().getId() + " sdf: " + System.identityHashCode(sdf));

        }).start());
    }



}
