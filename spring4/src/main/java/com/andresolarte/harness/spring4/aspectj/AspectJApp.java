package com.andresolarte.harness.spring4.aspectj;

import com.andresolarte.harness.spring4.aspectj.service.MyService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class AspectJApp {


    @Inject
    private MyService testService;

    public void run() throws Exception {
        System.out.println(testService.buildMessage());

        System.out.println(testService.getMessage());
    }
}
