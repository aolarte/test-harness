package com.andresolarte.harness.spring4.empty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmptyApp {

    @Autowired
    EmptyService emptyService;

    public void run() {

    }
}
