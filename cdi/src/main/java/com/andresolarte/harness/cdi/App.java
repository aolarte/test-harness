package com.andresolarte.harness.cdi;

import com.andresolarte.harness.cdi.services.TestService;
import com.andresolarte.harness.cdi.services.TestService2;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class App {
    @Inject
    private TestService testService;

    @Inject
    private TestService2 testService2;


    public void run() {
        System.out.println(testService.buildMessage());

        testService.executeSubService();
        testService2.executeSubService();
    }
}
