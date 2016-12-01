package com.andresolarte.harness.springboot.service;

import org.springframework.stereotype.Component;

@Component
public class TestService implements ITestService {

    public String buildMessage() {
        return "Hello World!";
    }
}
