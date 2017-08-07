package com.andresolarte.harness.spring4.aspectj.service;


import org.springframework.stereotype.Component;

@Component
public class MyService {

    public String buildMessage() {
        return "Hello World!";
    }

    public String getMessage() {
        return "Hello World!";
    }
}
