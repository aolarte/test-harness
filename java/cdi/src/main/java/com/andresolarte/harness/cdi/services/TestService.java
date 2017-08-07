package com.andresolarte.harness.cdi.services;

import com.andresolarte.harness.cdi.HTML;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@HTML
public class TestService {


    public String buildMessage() {
        return "Hello World!";
    }
}
