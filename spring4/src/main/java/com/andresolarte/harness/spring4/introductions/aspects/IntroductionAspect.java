package com.andresolarte.harness.spring4.introductions.aspects;

import com.andresolarte.harness.spring4.introductions.extensions.UsageAudit;
import com.andresolarte.harness.spring4.introductions.extensions.UsageAuditImpl;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class IntroductionAspect {

    @DeclareParents(value = "com.andresolarte.harness.spring4.introductions.service.BaseService*", defaultImpl = UsageAuditImpl.class)
    private UsageAudit audit;


    @Pointcut("execution(* com.andresolarte.harness.spring4.introductions.service.BaseService.buildMessage(..))")
    private void settingContent() {
    }


    @Before("settingContent() && this(audit)")
    private void onSettingContent( UsageAudit audit) throws Throwable {
        audit.audit();
    }
}