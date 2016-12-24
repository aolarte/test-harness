package com.andresolarte.harness.lang.java8.topic7_2;

import java.math.BigDecimal;

public class DefaultMethodTest implements Runnable {

    interface ISimpleDataPoint {


        BigDecimal getData();

        default String formatDataPoint() {
            return String.format("%.2f", getData());
        }
    }

    interface IHighPrecisionDataPoint extends ISimpleDataPoint {
        @Override
        default String formatDataPoint() {
            return String.format("%.4f", getData());
        }
    }

    interface ICustomPrecisionDataPoint extends ISimpleDataPoint {
        String formatDataPoint();
    }

    public void run() {
        ISimpleDataPoint simpleDataPoint = new ISimpleDataPoint() {
            @Override
            public BigDecimal getData() {
                return new BigDecimal(999);
            }
        };
        ISimpleDataPoint highPrecisionDataPoint = new IHighPrecisionDataPoint() {
            @Override
            public BigDecimal getData() {
                return new BigDecimal(999);
            }
        };
        ISimpleDataPoint customPrecisionDataPoint = new ICustomPrecisionDataPoint() {
            @Override
            public BigDecimal getData() {
                return new BigDecimal(999);
            }

            @Override
            public String formatDataPoint() {
                return String.format("Custom Value %.0f", getData());
            }
        };
        System.out.println("(Interface with default method) simpleDataPoint: " + simpleDataPoint.formatDataPoint());
        System.out.println("(Interface overriding parent's default method) highPrecisionDataPoint: " + highPrecisionDataPoint.formatDataPoint());
        System.out.println("(Anonymous class overriding parent's abstract method) customPrecisionDataPoint: " + customPrecisionDataPoint.formatDataPoint());
    }
}
