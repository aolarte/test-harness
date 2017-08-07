package com.andresolarte.harness.spring4.profiles.services;


import com.andresolarte.harness.spring4.profiles.annotations.Dev;
import org.springframework.stereotype.Component;

@Component
@Dev
public class DevService implements ITestService{

    @Override
    public String buildMessage() {
        return "DEV Service";
    }
}
