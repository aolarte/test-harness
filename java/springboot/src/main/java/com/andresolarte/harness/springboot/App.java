package com.andresolarte.harness.springboot;

import com.andresolarte.harness.springboot.service.ITestService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class App {

    @Inject
    private ITestService testService;

    public void run() {
        System.out.println(testService.buildMessage());
    }
}
