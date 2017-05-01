package com.andresolarte.harness.spring4.interceptors;

import com.andresolarte.harness.spring4.interceptors.service.InterceptedTestService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class InterceptorApp {


    @Inject
    private InterceptedTestService testService;

    public void run() throws Exception {
        System.out.println(testService.buildMessage());
    }
}
