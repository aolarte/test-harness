package com.andresolarte.harness.spring4.qualifiers.services;

import com.andresolarte.harness.spring4.qualifiers.qualifiers.ProdQualifier;
import com.andresolarte.harness.spring4.qualifiers.qualifiers.TestQualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@ProdQualifier
@Component
public class ProdService implements IService{
    @Override
    public String getText() {
        return "Prod";
    }
}
