package com.andresolarte.harness.cdi.services;

import com.andresolarte.harness.cdi.HTML;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@HTML
public class TestService {

    @Inject
    private SubService subService;

    public void executeSubService() {
        subService.execute();
    }

    public String buildMessage() {
        return "Hello World!";
    }
}
