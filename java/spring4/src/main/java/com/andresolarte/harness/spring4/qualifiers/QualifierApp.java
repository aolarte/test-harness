package com.andresolarte.harness.spring4.qualifiers;

import com.andresolarte.harness.spring4.qualifiers.qualifiers.ProdQualifier;
import com.andresolarte.harness.spring4.qualifiers.qualifiers.TestQualifier;
import com.andresolarte.harness.spring4.qualifiers.services.IService;
import com.andresolarte.harness.spring4.qualifiers.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class QualifierApp {

    @Autowired
    @TestQualifier
    private IService testService;

    @Autowired
    @ProdQualifier
    private IService prodService;

    @Autowired
    private IService primaryService;

    @Autowired
    @Qualifier("testService")
    private IService testServiceByName;

    @Autowired
    private TestService testServiceByClass;

    public void run() {
        System.out.println("@TestQualifier: " + testService.getText());
        System.out.println("@ProdQualifier: " + prodService.getText());
        System.out.println("Primary: " + primaryService.getText());
        System.out.println("@Qualifier(\"testService\"): " + testServiceByName.getText());
        System.out.println("By Class (TestService): " + testServiceByClass.getText());
    }
}
