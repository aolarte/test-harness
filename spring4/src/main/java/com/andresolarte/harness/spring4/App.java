package com.andresolarte.harness.spring4;

import com.andresolarte.harness.spring4.service.TestService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class App {

    @Inject
    private TestService testService;

    public void run() {
        System.out.println(testService.buildMessage());
    }
}
