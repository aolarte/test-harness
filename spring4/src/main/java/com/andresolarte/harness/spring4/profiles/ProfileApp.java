package com.andresolarte.harness.spring4.profiles;

import com.andresolarte.harness.spring4.profiles.services.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ProfileApp {
    @Autowired
    ConfigurableEnvironment configurableEnvironment;

    @Autowired
    ITestService testService;

    public void run() {
        System.out.println("Profile: " + Stream.of(configurableEnvironment.getActiveProfiles())
                .collect(Collectors.joining(",")));
        System.out.println("Service: " + testService.buildMessage());
    }
}
