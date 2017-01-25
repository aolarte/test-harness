package com.andresolarte.harness.spring.integration;

import com.andresolarte.harness.spring.integration.config.AppConfig;
import com.andresolarte.harness.spring.integration.service.IGateway;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.messaging.Message;


public class SpringIntegrationTest {
    public static void main (String... args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        IGateway gateway = context.getBean(IGateway.class);
        Message o = gateway.sendMessage("TEST");
        System.out.println("Result: " + o);
    }
}
