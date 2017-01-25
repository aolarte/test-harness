package com.andresolarte.harness.spring4.service;


import com.andresolarte.harness.spring4.interceptor.HTMLPrettify;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class OldTestService implements ITestService {

    @HTMLPrettify
    public String buildMessage() {
        return "**Deprecated :( ** ";
    }
}
