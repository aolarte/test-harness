package com.andresolarte.harness.guice;

import com.andresolarte.harness.guice.service.TestService;

import javax.inject.Inject;

public class App {

    @Inject
    private TestService testService;

    public void run() {
        System.out.println(testService.buildMessage());
    }
}
