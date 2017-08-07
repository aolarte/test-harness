package com.andresolarte.harness.cdi;

import com.andresolarte.harness.cdi.services.TestService;
import com.andresolarte.harness.cdi.services.TestService2;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@ApplicationScoped
public class App {
    @Inject
    private TestService testService;

    @Inject
    private Instance<TestService2> testService2Instance;


    public void run() {
        System.out.println(testService.buildMessage());

        System.out.println(testService2Instance.get().buildMessage());
    }
}
