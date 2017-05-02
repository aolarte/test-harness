package com.andresolarte.harness.spring4.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiesApp {

    @Value("${key1}")
    private String value;

    public void run() {
        System.out.println("Value: " + value);
    }
}
