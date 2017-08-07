package com.andresolarte.harness.guice.service;

public class SecurityProvider implements ISecurity {
    public void verifySecurity() {
        System.out.println("Evaluating security");
    }
}
