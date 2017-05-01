package com.andresolarte.harness.spring4.circular;

import com.andresolarte.harness.spring4.circular.service.ServiceA;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackageClasses = Spring4CircularTest.class)
public class Spring4CircularTest {


    public static void main(String... args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(Spring4CircularTest.class);

        ServiceA serviceA = context.getBean(ServiceA.class);
        System.out.println(serviceA.getText());
    }
}
