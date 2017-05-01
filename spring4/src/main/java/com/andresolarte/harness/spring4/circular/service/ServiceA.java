package com.andresolarte.harness.spring4.circular.service;

import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class ServiceA {
    private final ServiceB serviceB;

    @Inject
    public ServiceA(ServiceB serviceB) {
        this.serviceB = serviceB;
    }

    public String getText() {
        return serviceB.getText();
    }

    public String getDefaultText() {
        return "hello";
    }
}
