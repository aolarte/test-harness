package com.andresolarte.harness.cdi.services;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TestService2 {

    public String buildMessage() {
        return "Hello World!";
    }

}
