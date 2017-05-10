package com.andresolarte.harness.spring4.introductions;

import com.andresolarte.harness.spring4.introductions.extensions.UsageAudit;
import com.andresolarte.harness.spring4.introductions.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IntroductionApp {


    @Autowired
    private BaseService testService;



    public void run() throws Exception {
        System.out.println(testService.buildMessage());
        UsageAudit audit = (UsageAudit)testService;
        audit.audit();
    }
}
