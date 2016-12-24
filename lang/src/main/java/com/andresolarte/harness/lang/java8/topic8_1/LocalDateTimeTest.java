package com.andresolarte.harness.lang.java8.topic8_1;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.UnsupportedTemporalTypeException;

public class LocalDateTimeTest implements Runnable {
    public void run() {
        System.out.println("===LocalDateTime===");
        System.out.println(LocalDate.of(2015, 12, 25));
        System.out.println(LocalDate.of(2015, Month.DECEMBER, 25));
        LocalDate now = LocalDate.now();
        System.out.println("LocalDate Now: " + now);
        System.out.println(now.getYear() + " " + now.getMonth() + " " + now.getDayOfMonth());
        System.out.println(now.get(ChronoField.YEAR) + " " + now.get(ChronoField.MONTH_OF_YEAR) + " " + now.get(ChronoField.DAY_OF_MONTH));


        System.out.println(LocalTime.of(16, 53, 34));
        LocalTime nowTime = LocalTime.now();
        System.out.println("LocalTime Now: " + nowTime);
        System.out.println(nowTime.get(ChronoField.HOUR_OF_DAY) + ":" + nowTime.get(ChronoField.MINUTE_OF_HOUR) + ":" +
                nowTime.get(ChronoField.SECOND_OF_MINUTE) + "." + nowTime.get(ChronoField.NANO_OF_SECOND));
        try {
            System.out.println("LocalTime invalid fields: " + nowTime.get(ChronoField.YEAR));
        } catch (UnsupportedTemporalTypeException e) {
            System.out.println("Exception will be thrown with unsupported temporal types");
        }
        LocalDateTime nowDateTime = LocalDateTime.now();
        System.out.println("LocalDateTime Now:" + nowDateTime.get(ChronoField.YEAR) + " " + nowDateTime.get(ChronoField.MONTH_OF_YEAR) +
                " " + nowDateTime.get(ChronoField.DAY_OF_MONTH) + nowDateTime.get(ChronoField.HOUR_OF_DAY) + ":" + nowDateTime.get(ChronoField.MINUTE_OF_HOUR) + ":" +
                nowDateTime.get(ChronoField.SECOND_OF_MINUTE) + "." + nowDateTime.get(ChronoField.NANO_OF_SECOND));

        System.out.println("Instant: " + Instant.now().getEpochSecond());

        Period period = Period.between(LocalDate.now(), LocalDate.of(2015, 12, 25));
        System.out.println("Period: " + period.get(ChronoUnit.MONTHS) + " months " + period.get(ChronoUnit.DAYS) + " days");

        Duration duration = Duration.between(Instant.EPOCH, Instant.now());
        System.out.println("Duration from epoch: " + duration.get(ChronoUnit.SECONDS) + " seconds");

    }
}
