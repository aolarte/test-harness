package com.andresolarte.harness.spring4;

import com.andresolarte.harness.spring4.config.Spring4TestConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Spring4Test {


    public static void main(String... args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(Spring4TestConfig.class);

        App app = context.getBean(App.class);
        app.run();
    }
}
