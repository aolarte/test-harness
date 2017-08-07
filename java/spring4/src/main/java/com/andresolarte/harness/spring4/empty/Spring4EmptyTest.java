package com.andresolarte.harness.spring4.empty;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackageClasses = Spring4EmptyTest.class)
public class Spring4EmptyTest {

    public static void main(String... args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(Spring4EmptyTest.class);
        EmptyApp app = context.getBean(EmptyApp.class);
        app.run();
    }

}
