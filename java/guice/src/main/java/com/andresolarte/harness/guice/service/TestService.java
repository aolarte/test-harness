package com.andresolarte.harness.guice.service;


import com.andresolarte.harness.guice.interceptor.HTMLPrettify;

public class TestService {


    @HTMLPrettify
    public String buildMessage() {
        return "Hello World!";
    }
}
