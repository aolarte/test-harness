package com.andresolarte.harness.spring4.profiles.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class ProdService implements ITestService {
    @Override
    public String buildMessage() {
        return "PROD Service";
    }
}
