package com.andresolarte.harness.guice;

import com.andresolarte.harness.guice.service.IProvider;
import com.andresolarte.harness.guice.service.TestService;

import javax.inject.Inject;

public class App {

    @Inject
    private TestService testService;
    @Inject
    private IProvider provider;

    public void run() {
        System.out.println(testService.buildMessage());
        System.out.println(provider.provideMessage());
    }
}
