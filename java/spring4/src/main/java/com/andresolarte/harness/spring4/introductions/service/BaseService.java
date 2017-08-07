package com.andresolarte.harness.spring4.introductions.service;


import org.springframework.stereotype.Component;

@Component
public class BaseService {

    public String buildMessage() {
        return "Hello World!";
    }
}
