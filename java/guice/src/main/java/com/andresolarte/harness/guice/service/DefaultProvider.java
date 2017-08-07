package com.andresolarte.harness.guice.service;

import javax.inject.Inject;

public class DefaultProvider implements IProvider {

    private final ISecurity security;

    @Inject
    public DefaultProvider(ISecurity security) {
        this.security = security;
    }


    @Override
    public String provideMessage() {
        security.verifySecurity();
        return "Default";
    }
}
