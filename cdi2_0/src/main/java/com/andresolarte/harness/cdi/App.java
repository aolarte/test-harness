package com.andresolarte.harness.cdi;

import com.andresolarte.harness.cdi.services.TestService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class App {
    private final TestService testService;

    @Inject
    public App(TestService testService) {
        this.testService = testService;
    }

    public void run() {
        System.out.println(testService.buildMessage());
    }
}
