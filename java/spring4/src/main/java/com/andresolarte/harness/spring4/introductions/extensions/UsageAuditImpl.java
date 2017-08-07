package com.andresolarte.harness.spring4.introductions.extensions;

public class UsageAuditImpl implements UsageAudit {
    @Override
    public void audit() {
        System.out.println("Service invoked");
    }
}
