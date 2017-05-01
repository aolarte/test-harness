package com.andresolarte.harness.spring4.interceptors.service;


import com.andresolarte.harness.spring4.interceptors.interceptor.HTMLPrettify;
import org.springframework.stereotype.Component;

@Component
public class InterceptedTestService {

    @HTMLPrettify
    public String buildMessage() {
        return "Hello World!";
    }
}
