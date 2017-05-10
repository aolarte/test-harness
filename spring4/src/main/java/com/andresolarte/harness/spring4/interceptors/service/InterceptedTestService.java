package com.andresolarte.harness.spring4.interceptors.service;


import com.andresolarte.harness.spring4.interceptors.interceptor.HTMLPrettify;
import org.springframework.stereotype.Component;

@HTMLPrettify
@Component
public class InterceptedTestService {

    @HTMLPrettify
    public String buildMessage1() {
        return "Hello World!";
    }

    public String buildMessage2() {
        return "Hello World!";
    }
}
