package com.andresolarte.harness.spring4.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiesApp {

    @Value("${key1}")
    private String value1;

    @Value("${key2}")
    private String value2;

    public void run() {
        System.out.println("Value1: " + value1);
        System.out.println("Value2: " + value2);
    }
}
