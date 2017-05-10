package com.andresolarte.harness.spring4.processor;

import com.andresolarte.harness.spring4.processor.service.MyService;
import com.andresolarte.harness.spring4.profiles.ProfileApp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;


@Configuration
@ComponentScan(basePackageClasses = Spring4ProcessorTest.class)
public class Spring4ProcessorTest {


    public static void main(String... args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(Spring4ProcessorTest.class);
        MyService service = context.getBean(MyService.class);
        service.run();
    }

}
