package com.andresolarte.harness.cdi.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TestService2 {

    @Inject
    private SubService subService;


    public void executeSubService() {
        subService.execute();
    }


}
