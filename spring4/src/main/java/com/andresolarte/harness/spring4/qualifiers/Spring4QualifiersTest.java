package com.andresolarte.harness.spring4.qualifiers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackageClasses = Spring4QualifiersTest.class)
public class Spring4QualifiersTest {

    public static void main(String... args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(Spring4QualifiersTest.class);
        QualifierApp app = context.getBean(QualifierApp.class);
        app.run();
    }

}
