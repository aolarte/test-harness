package com.andresolarte.harness.spring4.factories.service;


import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class OldTestService implements ITestService {


    public String buildMessage() {
        return "**Deprecated :( ** ";
    }
}
