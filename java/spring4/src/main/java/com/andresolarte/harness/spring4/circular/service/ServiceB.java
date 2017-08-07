package com.andresolarte.harness.spring4.circular.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
//At least one the service needs to be a proxy
public class ServiceB {

    private final ServiceA serviceA;

    @Inject
    public ServiceB(ServiceA serviceA) {
        this.serviceA = serviceA;
    }

    public String getText() {
        return serviceA.getDefaultText() + " world";
    }

}
