package com.andresolarte.harness.spring4.service;


import com.andresolarte.harness.spring4.interceptor.HTMLPrettify;
import org.springframework.stereotype.Component;

@Component
public class TestService {

    @HTMLPrettify
    public String buildMessage() {
        return "Hello World!";
    }
}
