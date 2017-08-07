package com.andresolarte.harness.spring4.processor.service;

import com.andresolarte.harness.spring4.processor.annotation.Log;
import org.springframework.stereotype.Component;

@Log
@Component
public class MyService {
    public void run() {
        System.out.println("Running");
    }
}
