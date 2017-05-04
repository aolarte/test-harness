package com.andresolarte.harness.spring4.qualifiers.services;

import com.andresolarte.harness.spring4.qualifiers.qualifiers.TestQualifier;
import org.springframework.stereotype.Component;

@TestQualifier
@Component
public class TestService  implements IService{
    @Override
    public String getText() {
        return "Test";
    }
}
