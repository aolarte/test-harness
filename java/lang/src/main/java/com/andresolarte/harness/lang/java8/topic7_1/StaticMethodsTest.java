package com.andresolarte.harness.lang.java8.topic7_1;

import java.math.BigDecimal;


public class StaticMethodsTest implements Runnable {

    interface DataPoint {

        void setData(String name);

        String getData();

        static String getDefaultDecimalFormat() {
            return "%.2f";
        }


    }

    class CustomDataPoint implements DataPoint {

        private BigDecimal data;

        @Override
        public void setData(String data) {
            this.data = new BigDecimal(data);
        }

        @Override
        public String getData() {
            return String.format(DataPoint.getDefaultDecimalFormat(), data);
        }
    }

    public void run() {
        DataPoint customDataPoint = new CustomDataPoint();
        customDataPoint.setData("9999");
        System.out.println(customDataPoint.getData());
    }
}
