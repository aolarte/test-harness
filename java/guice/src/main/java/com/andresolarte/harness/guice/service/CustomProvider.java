package com.andresolarte.harness.guice.service;

import javax.inject.Inject;

public class CustomProvider implements IProvider{

    private final ISecurity security;

    @Inject
    public CustomProvider(ISecurity security) {
        this.security = security;
    }


    @Override
    public String provideMessage() {
        security.verifySecurity();
        return "custom";
    }
}
